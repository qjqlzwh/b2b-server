package com.cow.feign.user;

import com.cow.po.vo.user.UserVo;
import com.cow.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户
 */
@FeignClient("cow-user")
public interface UserFeignClient {

    @GetMapping("/user/detail/{id}")
    R<UserVo> detail(@PathVariable("id") Long id);
}
