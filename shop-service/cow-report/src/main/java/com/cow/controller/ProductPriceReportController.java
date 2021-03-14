package com.cow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductPriceReportDTO;
import com.cow.resp.R;
import com.cow.service.ProductPriceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 产品价格报表
 */
@RestController
@RequestMapping("/productPriceReport")
public class ProductPriceReportController {

    @Autowired
    private ProductPriceReportService productPriceReportService;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(ProductPriceReportDTO productPriceReportDTO) {
        Page<Map<String, Object>> page = productPriceReportService.pageData(productPriceReportDTO);
        return R.ok().data(page);
    }
}
