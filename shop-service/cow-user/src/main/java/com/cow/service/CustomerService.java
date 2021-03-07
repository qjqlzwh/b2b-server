package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CustomerDTO;
import com.cow.po.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cow
 * @since 2021-02-01
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 列表数据
     * @param customerDTO
     * @return
     */
    Page<Map<String, Object>> pageData(CustomerDTO customerDTO);

    /**
     * 新增
     * @param customer
     */
    void add(Customer customer);

    /**
     * 更新
     * @param customer
     */
    void update(Customer customer);

    /**
     * 弹框分页数据
     * @param customerDTO
     * @return
     */
    Page<Map<String, Object>> pageDataPop(CustomerDTO customerDTO);
}
