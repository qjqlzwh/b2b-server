package com.cow.mapper;

import com.cow.po.pojo.ProductPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品价格 Mapper 接口
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
public interface ProductPriceMapper extends BaseMapper<ProductPrice> {

    /**
     * 根据主id获取客户明细
     * @param productPriceId
     * @return
     */
    List<Map<String, Object>> getCustomerItemById(Long productPriceId);

    /**
     * 根据主id获取产品明细
     * @param productPriceId
     * @return
     */
    List<Map<String, Object>> getProductItemById(Long productPriceId);
}
