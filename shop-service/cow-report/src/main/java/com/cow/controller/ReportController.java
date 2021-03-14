package com.cow.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductPriceReportDTO;
import com.cow.resp.R;
import com.cow.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  报表 前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-03-06
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(ProductPriceReportDTO productPriceReportDTO) {
        Page<Map<String, Object>> page = reportService.productPriceData(productPriceReportDTO);
        return R.ok().data(page);
    }
}

