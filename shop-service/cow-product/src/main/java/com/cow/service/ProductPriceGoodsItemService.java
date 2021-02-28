package com.cow.service;

import com.cow.po.pojo.ProductPriceGoodsItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品价格 - 商品明细 服务类
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
public interface ProductPriceGoodsItemService extends IService<ProductPriceGoodsItem> {

    /**
     * 根据主表id获取产品明细
     * @param productPriceId
     * @return
     */
    List<ProductPriceGoodsItem> listByProductPriceId(Long productPriceId);
}
