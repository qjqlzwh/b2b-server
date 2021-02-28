package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.feign.product.ProductFeignClient;
import com.cow.feign.product.ProductPriceFeignClient;
import com.cow.feign.vo.ProductPriceGoodsItemVo;
import com.cow.feign.vo.ProductPriceVo;
import com.cow.feign.vo.ProductVo;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.OrderDTO;
import com.cow.po.enums.CommonState;
import com.cow.po.enums.OrderState;
import com.cow.po.pojo.Order;
import com.cow.mapper.OrderMapper;
import com.cow.po.pojo.OrderItem;
import com.cow.service.OrderItemService;
import com.cow.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.util.CalculateUtils;
import com.cow.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private ProductFeignClient productFeignClient;
    @Autowired
    private ProductPriceFeignClient productPriceFeignClient;

    /**
     * 列表数据
     *
     * @param orderDTO
     * @return
     */
    @Override
    public Page<Order> pageData(OrderDTO orderDTO) {
        QueryWrapper<Order> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "state", orderDTO.getState());
        QwUtils.eq(qw, "order_type", orderDTO.getOrderType());
        Page<Order> pageData = baseMapper.selectPage(orderDTO.page(), qw);
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
        // 删除旧的明细
        List<OrderItem> oldOrderItems = orderItemService.list(Wrappers.<OrderItem>lambdaQuery().eq(OrderItem::getOrderId, order.getId()));
        orderItemService.removeByIds(oldOrderItems);

        orderItemService.saveBatch(order.getOrderItemList());
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
    public void audit(Order order) {
        order.setState(OrderState.AUDIT.getState());
        order.setAuditTime(new Date());
        baseMapper.updateById(order);
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
            // 订单提交减库存
            if (order.getState() == OrderState.SUBMIT.getState()) {

            }

            orderItem.setPrice(goodsItemVo.getPrice());

            orderItem.setTotalPrice(CalculateUtils.multiply(orderItem.getPrice(), orderItem.getQuantity(), 6));
            orderTotalPrice = CalculateUtils.add(orderTotalPrice, orderItem.getTotalPrice());
        }
        order.setTotalPrice(orderTotalPrice);
    }
}
