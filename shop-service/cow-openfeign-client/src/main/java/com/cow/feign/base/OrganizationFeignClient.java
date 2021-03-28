package com.cow.feign.base;

import com.cow.po.vo.base.OrganizationVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 机构
 */
@FeignClient("cow-base")
public interface OrganizationFeignClient {

    @GetMapping("/organization/info/{id}")
    OrganizationVo info(@PathVariable("id") Long id);

}
