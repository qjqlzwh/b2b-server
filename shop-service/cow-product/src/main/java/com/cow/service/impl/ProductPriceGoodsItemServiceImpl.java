package com.cow.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cow.po.pojo.ProductPriceCustomerItem;
import com.cow.po.pojo.ProductPriceGoodsItem;
import com.cow.mapper.ProductPriceGoodsItemMapper;
import com.cow.service.ProductPriceGoodsItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品价格 - 商品明细 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
@Service
public class ProductPriceGoodsItemServiceImpl extends ServiceImpl<ProductPriceGoodsItemMapper, ProductPriceGoodsItem> implements ProductPriceGoodsItemService {

    /**
     * 根据主表id获取产品明细
     *
     * @param productPriceId
     * @return
     */
    @Override
    public List<ProductPriceGoodsItem> listByProductPriceId(Long productPriceId) {
        return baseMapper.selectList(Wrappers.<ProductPriceGoodsItem>lambdaQuery().eq(ProductPriceGoodsItem::getProductPrice, productPriceId));
    }
}
