package com.cow.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.constant.MqTopic;
import com.cow.exception.ExceptionUtils;
import com.cow.feign.product.ProductFeignClient;
import com.cow.feign.product.ProductPriceFeignClient;
import com.cow.feign.user.CustomerFeignClient;
import com.cow.po.vo.product.ProductPriceGoodsItemVo;
import com.cow.po.vo.product.ProductPriceVo;
import com.cow.po.vo.product.ProductVo;
import com.cow.po.dto.OrderDTO;
import com.cow.po.enums.CommonState;
import com.cow.po.enums.OrderState;
import com.cow.po.pojo.Order;
import com.cow.mapper.OrderMapper;
import com.cow.po.pojo.OrderItem;
import com.cow.po.vo.order.OrderVo;
import com.cow.resp.R;
import com.cow.service.OrderItemService;
import com.cow.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.util.CalculateUtils;
import com.cow.util.IdUtils;
import com.cow.util.WebUtils;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-19
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CustomerFeignClient customerFeignClient;
    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private ProductPriceFeignClient productPriceFeignClient;
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 列表数据
     *
     * @param orderDTO
     * @return
     */
    @Override
    public Page<Map<String, Object>> pageData(OrderDTO orderDTO) {
//        QueryWrapper<Order> qw = new QueryWrapper<>();
//        QwUtils.eq(qw, "state", orderDTO.getState());
//        QwUtils.eq(qw, "order_type", orderDTO.getOrderType());
//        Page<Order> pageData = baseMapper.selectPage(orderDTO.page(), qw);
        Page<Map<String, Object>> pageData = baseMapper.pageData(orderDTO.page(), orderDTO);
        return pageData;
    }

    /**
     * 增加
     *
     * @param order
     */
    @Override
    @Transactional
    public void add(Order order) {
        order.setSn(IdUtils.snfId());
        order.setState(OrderState.SAVE.getState());
        handleSaveOrUpdate(order);
        baseMapper.insert(order);
        for (OrderItem orderItem: order.getOrderItemList()) {
            orderItem.setOrderId(order.getId());
        }
        orderItemService.saveBatch(order.getOrderItemList());
    }

    /**
     * 更新
     *
     * @param order
     */
    @Override
    @Transactional
    public void update(Order order) {
        handleSaveOrUpdate(order);
        baseMapper.updateById(order);

        List<Long> orderItemIds = order.getOrderItemList()
                .stream()
                .filter(orderItem -> orderItem.getId() != null)
                .map(OrderItem::getId)
                .collect(Collectors.toList());

        // 删除旧的明细
        if (CollectionUtils.isEmpty(orderItemIds)) {
            orderItemService.remove(
                    Wrappers.<OrderItem>lambdaQuery()
                            .eq(OrderItem::getOrderId, order.getId())
            );
        } else {
            orderItemService.remove(
                    Wrappers.<OrderItem>lambdaQuery()
                            .eq(OrderItem::getOrderId, order.getId())
                            .notIn(OrderItem::getId, orderItemIds)
            );
        }

        orderItemService.saveOrUpdateBatch(order.getOrderItemList());
    }

    /**
     * 提交
     *
     * @param order
     */
    @Override
    @Transactional
    public void submit(Order order) {
        order.setState(OrderState.SUBMIT.getState());
        this.update(order);
    }

    /**
     * 审核
     *
     * @param order
     */
    @Override
    @Transactional
    @GlobalTransactional
    public void audit(Order order) {
        String xid = RootContext.getXID();
        System.out.println("xid: " + xid);

        order.setState(OrderState.AUDIT.getState());
        order.setAuditTime(new Date());

        // 扣减库存
        for (OrderItem orderItem : order.getOrderItemList()) {
            R r = productPriceFeignClient.increaseProductUseQuantity(orderItem.getProductPriceGoodsItem(), orderItem.getQuantity());
            if (r.getCode() != 200) {
                ExceptionUtils.throwRowException(r.getMessage());
            }
        }

        baseMapper.updateById(order);

//        ExceptionUtils.throwRowException("自定义异常！-------------------");

        // 发送MQ
        OrderVo orderVo = new OrderVo();
        orderVo.setId(order.getId());
        orderVo.setSn(order.getSn());
        orderVo.setSalesman(order.getSalesman());
        orderVo.setCustomer(order.getCustomer());
        orderVo.setCreator(WebUtils.getLoginUsername());
        orderVo.setContent("订单审核通过，订单号：" + order.getSn());
        orderVo.setSubject("订单审核");
        rocketMQTemplate.convertAndSend(MqTopic.ORDER_AUDIT, orderVo);
    }

    private void handleSaveOrUpdate(Order order) {
        Assert.notNull(order.getSalesman(), "业务员不能为空！");
        Assert.notNull(order.getCustomerAddress(), "收货地址不能为空！");
        Assert.notEmpty(order.getOrderItemList(), "订单明细不能为空！");

        String msg = "";
        BigDecimal orderTotalPrice = BigDecimal.ZERO;
        int seq = 1;
        for (OrderItem orderItem : order.getOrderItemList()) {
            orderItem.setSeq(seq++);

            // 处理产品
            ProductVo product = productFeignClient.info(orderItem.getProduct());
            orderItem.setProductName(product.getDname());
            orderItem.setProductCode(product.getDcode());
            orderItem.setProductModel(product.getModel());
            orderItem.setProductUnit(product.getUnit());
            orderItem.setOrderId(order.getId());

            // 产品价格校验
            msg = "产品名称：" + product.getDname() + ", 产品编码：" + product.getDcode() + " ";
            Assert.notNull(orderItem.getProductPriceGoodsItem(), msg + "价格异常");
            ProductPriceVo productPriceVo = productPriceFeignClient.info(orderItem.getProductPriceId()).getData();
            Assert.notNull(productPriceVo, msg + "价格表异常");
            Assert.isTrue(productPriceVo.getState() == CommonState.AUDIT.getState(), msg + "价格表无效");
            Assert.notNull(productPriceFeignClient.getValidCustomerItem(orderItem.getProductPriceId(), order.getCustomer()), msg + "价格表中客户已失效");

            // 价格明细
            ProductPriceGoodsItemVo goodsItemVo = productPriceFeignClient.infoProductItem(orderItem.getProductPriceGoodsItem()).getData();
            Assert.notNull(goodsItemVo, msg + "价格异常");
            Assert.isTrue(goodsItemVo.getState() == CommonState.AUDIT.getState(), msg + "价格无效");
            // 订单提交增加特价已使用数量(减库存)
            if (order.getState() == OrderState.SUBMIT.getState()) {
                productPriceFeignClient.increaseProductUseQuantity(orderItem.getProductPriceGoodsItem(), orderItem.getQuantity());
            }

            orderItem.setPrice(goodsItemVo.getPrice());
            orderItem.setTotalPrice(CalculateUtils.multiply(orderItem.getPrice(), orderItem.getQuantity(), 6));
            orderTotalPrice = CalculateUtils.add(orderTotalPrice, orderItem.getTotalPrice());
        }
        order.setTotalPrice(orderTotalPrice);
    }
}
