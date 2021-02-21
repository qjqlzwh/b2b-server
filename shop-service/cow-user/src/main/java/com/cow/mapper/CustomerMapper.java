package com.cow.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CustomerDTO;
import com.cow.po.pojo.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author row
 * @since 2021-02-01
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    Page<Map<String, Object>> pageDataPop(IPage<Customer> page, CustomerDTO customerDTO);
}
