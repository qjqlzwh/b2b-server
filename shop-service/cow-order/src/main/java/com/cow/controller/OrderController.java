package com.cow.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.feign.base.OrganizationFeignClient;
import com.cow.feign.user.CustomerFeignClient;
import com.cow.po.vo.user.CustomerVo;
import com.cow.mybatis.Rc;
import com.cow.po.dto.OrderDTO;
import com.cow.po.pojo.Order;
import com.cow.po.pojo.OrderItem;
import com.cow.po.vo.base.OrganizationVo;
import com.cow.service.OrderItemService;
import com.cow.service.OrderService;
import com.cow.resp.R;
import com.cow.util.HuExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CustomerFeignClient customerFeignClient;
    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(OrderDTO orderDTO) {
        Page<Map<String, Object>> page = orderService.pageData(orderDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody Order order) {
        orderService.add(order);
        return R.ok().data(order.getId());
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
    public R audit(HttpServletRequest request, Long id) {
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
        Map<String, Object> orderMap = BeanUtil.beanToMap(order);
        CustomerVo customerVo = customerFeignClient.info(order.getCustomer()).getData();
        OrganizationVo organizationVo = organizationFeignClient.info(customerVo.getOrganization());
        List<OrderItem> orderItems = orderItemService.list(Wrappers.<OrderItem>lambdaQuery().eq(OrderItem::getOrderId, id));
        orderMap.put("organization", customerVo.getOrganization());
        orderMap.put("organizationName", organizationVo.getDname());
        orderMap.put("orderItemList", orderItems);
        return R.ok().data(orderMap);
    }

    /**
     * 导出
     * @param response
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response, OrderDTO orderDTO) {
        orderDTO.setIsPage(false);
        Rc rc = orderService.pageDataDl(orderDTO);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("dname", "产品名称");
        map.put("dcode", "产品编码");
        HuExcelUtils.exportExcel(response, "产品", rc.getListData(), map, null);
    }

}

