package com.cow.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductDTO;
import com.cow.po.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 加载弹框列表数据(带价格产品，如订单选产品)
     * @param page
     * @param productDTO
     * @return
     */
    Page<Map<String, Object>> popPriceList(Page page, ProductDTO productDTO);
}
