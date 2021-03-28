package com.cow.feign.user;

import com.cow.po.vo.user.CustomerVo;
import com.cow.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 客户
 */
@FeignClient("cow-user")
public interface CustomerFeignClient {

    @GetMapping("/customer/info/{id}")
    R<CustomerVo> info(@PathVariable("id") Long id);

}
