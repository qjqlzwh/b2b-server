package com.cow.service;

import com.cow.po.pojo.ProductPriceCustomerItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品价格 - 客户明细 服务类
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
public interface ProductPriceCustomerItemService extends IService<ProductPriceCustomerItem> {

    /**
     * 根据主表id获取客户明细
     * @param productPriceId
     * @return
     */
    List<ProductPriceCustomerItem> listByProductPriceId(Long productPriceId);
}
