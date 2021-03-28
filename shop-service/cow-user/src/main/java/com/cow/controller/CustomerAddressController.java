package com.cow.controller;


import com.cow.po.pojo.CustomerAddress;
import com.cow.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-02-02
 */
@RestController
@RequestMapping("/customerAddress")
public class CustomerAddressController {

    @Autowired
    private CustomerAddressService customerAddressService;

    /**
     * 根据id获取
     */
    @GetMapping("/info/{id}")
    public CustomerAddress info(@PathVariable("id") Long id) {
        return customerAddressService.getById(id);
    }

}

