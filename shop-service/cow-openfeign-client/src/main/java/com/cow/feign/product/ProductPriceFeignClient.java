package com.cow.feign.product;

import com.cow.feign.vo.ProductPriceCustomerItemVo;
import com.cow.feign.vo.ProductPriceGoodsItemVo;
import com.cow.feign.vo.ProductPriceVo;
import com.cow.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
}
