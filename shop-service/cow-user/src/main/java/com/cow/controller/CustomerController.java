package com.cow.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.feign.base.OrganizationFeignClient;
import com.cow.po.dto.CustomerDTO;
import com.cow.po.pojo.Customer;
import com.cow.po.pojo.CustomerAddress;
import com.cow.po.pojo.User;
import com.cow.resp.R;
import com.cow.service.CustomerAddressService;
import com.cow.service.CustomerService;
import com.cow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  客户前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-02-01
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(CustomerDTO customerDTO) {
        Page<Customer> page = customerService.pageData(customerDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody Customer customer) {
        customerService.add(customer);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody Customer customer) {
        customerService.update(customer);
        return R.ok();
    }

    /**
     * 根据id获取
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Customer customer = customerService.getById(id);
        customer.setCustomerAddressList(
                customerAddressService.list(
                        Wrappers.<CustomerAddress>lambdaQuery().eq(CustomerAddress::getCustomerId, id)));
        Map<String, Object> customerMap = BeanUtil.beanToMap(customer);
        customerMap.put("orgName", organizationFeignClient.info(customer.getOrganization()).getDname());
        User salesman = userService.getById(customer.getSalesman());
        customerMap.put("salesmanName", salesman.getUsername());
        return R.ok().data(customerMap);
    }
}

