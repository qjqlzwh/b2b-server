package com.cow.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.OrderDTO;
import com.cow.po.enums.OrderState;
import com.cow.po.pojo.Order;
import com.cow.service.OrderService;
import com.cow.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(OrderDTO orderDTO) {
        Page<Order> page = orderService.pageData(orderDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody Order order) {
        orderService.add(order);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody Order order) {
        orderService.update(order);
        return R.ok();
    }

    /**
     * 提交
     */
    @PostMapping("/submit")
    public R submit(@RequestBody Order order) {
        orderService.submit(order);
        return R.ok();
    }

    /**
     * 审核
     */
    @PostMapping("/audit")
    public R audit(Long id) {
        Order order = orderService.getById(id);
        orderService.audit(order);
        return R.ok();
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        return R.ok().data(order);
    }

}

