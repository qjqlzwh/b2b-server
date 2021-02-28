package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductDTO;
import com.cow.po.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  产品服务类
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
public interface ProductService extends IService<Product> {

    /**
     * 增加产品
     * @param product
     */
    void add(Product product);

    /**
     * 更新产品
     * @param product
     */
    void update(Product product);

    /**
     * 列表数据
     * @param productDTO
     * @return
     */
    Page<Product> pageData(ProductDTO productDTO);

    /**
     * 加载弹框列表数据(带价格产品，如订单选产品)
     * @param productDTO
     * @return
     */
    Page<Map<String, Object>> popPriceList(ProductDTO productDTO);
}
