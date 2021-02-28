package com.cow.feign.product;

import com.cow.feign.vo.ProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 产品
 */
@FeignClient("cow-product")
public interface ProductFeignClient {

    @GetMapping("/product/info/{id}")
    ProductVo info(@PathVariable("id") Long id);

}
