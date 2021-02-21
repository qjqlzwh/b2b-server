package com.cow.service.impl;

import com.cow.po.pojo.OrderItem;
import com.cow.mapper.OrderItemMapper;
import com.cow.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-19
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
