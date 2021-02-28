package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductPriceDTO;
import com.cow.po.pojo.ProductPrice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品价格 服务类
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
public interface ProductPriceService extends IService<ProductPrice> {

    /**
     * 列表数据
     * @param productPriceDTO
     * @return
     */
    Page<ProductPrice> pageData(ProductPriceDTO productPriceDTO);

    /**
     * 添加
     * @param productPrice
     */
    void add(ProductPrice productPrice);

    /**
     * 更新
     * @param productPrice
     */
    void update(ProductPrice productPrice);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Map<String, Object> detail(Long id);

    /**
     * 取消
     * @param productPrice
     */
    void cancel(ProductPrice productPrice);

    /**
     * 增加产品行的已使用数量
     * @param productItemId
     * @param useQuantity 增加的数量
     */
    void addProductItemUseQuantity(Long productItemId, BigDecimal useQuantity);
}
