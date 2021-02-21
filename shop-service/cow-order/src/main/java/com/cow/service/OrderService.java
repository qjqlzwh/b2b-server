package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.OrderDTO;
import com.cow.po.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author cow
 * @since 2021-02-19
 */
public interface OrderService extends IService<Order> {

    /**
     * 列表数据
     * @param orderDTO
     * @return
     */
    Page<Order> pageData(OrderDTO orderDTO);

    /**
     * 增加
     * @param order
     */
    void add(Order order);

    /**
     * 更新
     * @param order
     */
    void update(Order order);
}