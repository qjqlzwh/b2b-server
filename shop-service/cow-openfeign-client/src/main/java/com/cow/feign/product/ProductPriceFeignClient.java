package com.cow.feign.product;

import com.cow.po.vo.product.ProductPriceCustomerItemVo;
import com.cow.po.vo.product.ProductPriceGoodsItemVo;
import com.cow.po.vo.product.ProductPriceVo;
import com.cow.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * 产品
 */
@FeignClient("cow-product")
public interface ProductPriceFeignClient {

    /**
     * 获取价格表信息
     * @param id
     * @return
     */
    @GetMapping("/product-price/info/{id}")
    R<ProductPriceVo> info(@PathVariable("id") Long id);

    /**
     * 获取有效的客户明细
     * @param productPriceId 价格表id
     * @param customerId     客户id
     * @return
     */
    @GetMapping(value = "/product-price/getValidCustomerItem")
    R<ProductPriceCustomerItemVo> getValidCustomerItem(@RequestParam("productPriceId") Long productPriceId, @RequestParam("customerId") Long customerId);

    /**
     * 获取价格表客户明细信息
     * @param customerItemId
     * @return
     */
    @GetMapping("/product-price/infoCustomerItem/{customerItemId}")
    R<ProductPriceCustomerItemVo> infoCustomerItem(@PathVariable("customerItemId") Long customerItemId);

    /**
     * 获取价格表产品明细信息
     * @param productItemId
     * @return
     */
    @GetMapping("/product-price/infoProductItem/{productItemId}")
    R<ProductPriceGoodsItemVo> infoProductItem(@PathVariable("productItemId") Long productItemId);

    /**
     * 增加特价产品使用数量
     * @param productItemId 价格表产品明细行id
     * @param useQuantity   要增加的数量
     * @return
     */
    @PostMapping(value = "/product-price/increaseProductUseQuantity")
    R increaseProductUseQuantity(@RequestParam("productItemId") Long productItemId, @RequestParam("useQuantity") BigDecimal useQuantity);
}
