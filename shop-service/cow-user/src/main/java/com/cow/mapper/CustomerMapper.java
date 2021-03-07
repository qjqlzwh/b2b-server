package com.cow.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CustomerDTO;
import com.cow.po.pojo.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 客户 Mapper 接口
 * </p>
 *
 * @author row
 * @since 2021-02-01
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    /**
     * 客户列表数据
     * @param page
     * @param customerDTO
     * @return
     */
    Page<Map<String, Object>> pageData(Page page, CustomerDTO customerDTO);

    /**
     * 客户弹框数据
     * @param page
     * @param customerDTO
     * @return
     */
    Page<Map<String, Object>> pageDataPop(IPage<Customer> page, CustomerDTO customerDTO);

}
