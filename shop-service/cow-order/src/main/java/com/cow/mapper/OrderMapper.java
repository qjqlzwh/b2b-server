package com.cow.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.OrderDTO;
import com.cow.po.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author cow
 * @since 2021-02-19
 */
public interface OrderMapper extends BaseMapper<Order> {

    Page<Map<String, Object>> pageData(Page page, OrderDTO orderDTO);
}
