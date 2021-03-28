package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mybatis.Rc;
import com.cow.po.dto.OrderDTO;
import com.cow.po.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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
    Page<Map<String, Object>> pageData(OrderDTO orderDTO);

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

    /**
     * 提交
     * @param order
     */
    void submit(Order order);

    /**
     * 审核
     * @param order
     */
    void audit(Order order);

}
