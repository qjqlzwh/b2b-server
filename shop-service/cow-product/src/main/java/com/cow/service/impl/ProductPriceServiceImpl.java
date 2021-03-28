package com.cow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.exception.ExceptionUtils;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.ProductPriceDTO;
import com.cow.po.enums.CommonState;
import com.cow.po.pojo.Product;
import com.cow.po.pojo.ProductPrice;
import com.cow.mapper.ProductPriceMapper;
import com.cow.po.pojo.ProductPriceCustomerItem;
import com.cow.po.pojo.ProductPriceGoodsItem;
import com.cow.service.ProductPriceCustomerItemService;
import com.cow.service.ProductPriceGoodsItemService;
import com.cow.service.ProductPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.service.ProductService;
import com.cow.util.IdUtils;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品价格 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
@Service
public class ProductPriceServiceImpl extends ServiceImpl<ProductPriceMapper, ProductPrice> implements ProductPriceService {

    @Autowired
    private ProductPriceGoodsItemService productPriceGoodsItemService;
    @Autowired
    private ProductPriceCustomerItemService productPriceCustomerItemService;
    @Autowired
    private ProductService productService;

    /**
     * 列表数据
     */
    @Override
    public Page<ProductPrice> pageData(ProductPriceDTO productPriceDTO) {
        QueryWrapper<ProductPrice> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "sn", productPriceDTO.getSn());
        QwUtils.eq(qw, "state", productPriceDTO.getState());
        QwUtils.like(qw, "name", productPriceDTO.getDname());
        qw.orderByDesc("id");
        Page<ProductPrice> page = baseMapper.selectPage(productPriceDTO.page(), qw);
        return page;
    }

    /**
     * 添加
     * @param productPrice
     */
    @Override
    @Transactional
    public void add(ProductPrice productPrice) {
        productPrice.setSn(IdUtils.snfId());
        productPrice.setState(CommonState.SAVE.getState());
        handleSaveOrUpdate(productPrice);
        baseMapper.insert(productPrice);

        // 处理客户明细
        for (ProductPriceCustomerItem item : productPrice.getCustomerItem()) {
            item.setProductPrice(productPrice.getId());
        }
        productPriceCustomerItemService.saveBatch(productPrice.getCustomerItem());
        // 处理产品明细
        for (ProductPriceGoodsItem item : productPrice.getProductItem()) {
            item.setProductPrice(productPrice.getId());
        }
        productPriceGoodsItemService.saveBatch(productPrice.getProductItem());

    }

    /**
     * 更新
     * @param productPrice
     */
    @Override
    @Transactional
    public void update(ProductPrice productPrice) {
        handleSaveOrUpdate(productPrice);
        baseMapper.updateById(productPrice);

        // 删除旧的明细
        productPriceCustomerItemService.remove(Wrappers.<ProductPriceCustomerItem>lambdaQuery().eq(ProductPriceCustomerItem::getProductPrice, productPrice.getId()));
        productPriceGoodsItemService.remove(Wrappers.<ProductPriceGoodsItem>lambdaQuery().eq(ProductPriceGoodsItem::getProductPrice, productPrice.getId()));
        // 保存新的明细
        productPriceCustomerItemService.saveOrUpdateBatch(productPrice.getCustomerItem());
        productPriceGoodsItemService.saveOrUpdateBatch(productPrice.getProductItem());
    }

    private void handleSaveOrUpdate(ProductPrice productPrice) {
        Assert.hasText(productPrice.getDname(), "价目表名称不能为空！");
        Assert.notNull(productPrice.getStartTime(), "开始时间不能为空！");
        Assert.notNull(productPrice.getEndTime(), "结束时间不能为空！");
        Assert.notNull(productPrice.getOrganization(), "机构不能为空！");
        Assert.notEmpty(productPrice.getProductItem(), "产品明细不能为空！");
        Assert.notEmpty(productPrice.getCustomerItem(), "客户明细不能为空！");

        // 名称校重
        Integer count;
        if (productPrice.getId() == null) {
            count = baseMapper.selectCount(
                    Wrappers.<ProductPrice>lambdaQuery()
                            .eq(ProductPrice::getDname, productPrice.getDname()));
        } else {
            count = baseMapper.selectCount(
                    Wrappers.<ProductPrice>lambdaQuery()
                            .eq(ProductPrice::getDname, productPrice.getDname())
                            .ne(ProductPrice::getId, productPrice.getId()));
        }
        Assert.isTrue(count == 0, "价目表名称已经存在，请换一个名称！");

        // 处理客户明细
        int seq = 1;
        for (ProductPriceCustomerItem item : productPrice.getCustomerItem()) {
            item.setSeq(seq++);
            item.setState(productPrice.getState());
            item.setProductPrice(productPrice.getId());
        }

        // 处理产品明细
        seq = 1;
        for (ProductPriceGoodsItem item : productPrice.getProductItem()) {
            item.setSeq(seq++);
            item.setState(productPrice.getState());
            item.setProductPrice(productPrice.getId());
            item.setUseQuantity(BigDecimal.ZERO);
            Assert.notNull(item.getPrice(), "价格不能为空");
            Assert.notNull(item.getQuantity(), "数量不能为空");
        }
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> detail(Long id) {
        ProductPrice productPrice = baseMapper.selectById(id);
        Map<String, Object> productPriceMap = BeanUtil.beanToMap(productPrice);
        List<Map<String, Object>> customerItem = baseMapper.getCustomerItemById(id);
        List<Map<String, Object>> productItem = baseMapper.getProductItemById(id);
        productPriceMap.put("customerItem", customerItem);
        productPriceMap.put("productItem", productItem);
        return productPriceMap;
    }

    /**
     * 取消
     *
     * @param productPrice
     */
    @Override
    @Transactional
    public void cancel(ProductPrice productPrice) {
        productPrice.setState(CommonState.CANCEL.getState());
        List<ProductPriceCustomerItem> customerItems = productPriceCustomerItemService.listByProductPriceId(productPrice.getId());
        List<ProductPriceGoodsItem> productItems = productPriceGoodsItemService.listByProductPriceId(productPrice.getId());
        // 处理客户明细
        for (ProductPriceCustomerItem item : customerItems) {
            item.setState(productPrice.getState());
        }
        // 处理产品明细
        for (ProductPriceGoodsItem item : productItems) {
            item.setState(productPrice.getState());
        }
        baseMapper.updateById(productPrice);
        productPriceCustomerItemService.updateBatchById(customerItems);
        productPriceGoodsItemService.updateBatchById(productItems);
    }

    /**
     * 增加产品行的已使用数量
     *
     * @param productItemId 产品明细行id
     * @param useQuantity   增加的数量
     */
    @Override
    @Transactional
    public void increaseProductUseQuantity(Long productItemId, BigDecimal useQuantity) {
        String xid = RootContext.getXID();
        System.out.println("xid: " + xid);

//        ExceptionUtils.throwRowException("自定义异常！123-------------------");

        ProductPriceGoodsItem goodsItem = productPriceGoodsItemService.getById(productItemId);
        Product product = productService.getById(goodsItem.getProduct());
        String msg = "产品【"+ product.getDname() +"】";
        Assert.isTrue(goodsItem.getState() == CommonState.AUDIT.getState(), msg + "价格已经过期");
        BigDecimal totalUseQuantity = useQuantity.add(goodsItem.getUseQuantity());
        if (totalUseQuantity.compareTo(goodsItem.getQuantity()) > 0) {
            ExceptionUtils.throwRowException(msg + "库存不足");
        }
        goodsItem.setUseQuantity(totalUseQuantity);
        productPriceGoodsItemService.updateById(goodsItem);
    }
}
