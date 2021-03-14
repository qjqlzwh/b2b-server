package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cow.po.dto.ProductPriceReportDTO;
import com.cow.po.pojo.Report;

import java.util.Map;

public interface ProductPriceReportService extends IService<Object> {

    Page<Map<String, Object>> pageData(ProductPriceReportDTO productPriceReportDTO);
}
