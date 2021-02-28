package com.cow.feign.user;

import com.cow.feign.vo.CustomerAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 客户地址
 */
@FeignClient("cow-user")
public interface CustomerAddressFeignClient {

    @GetMapping("/customerAddress/info/{id}")
    CustomerAddressVo info(@PathVariable("id") Long id);

}
