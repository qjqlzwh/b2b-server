package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductPriceReportDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cow.po.pojo.Report;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cow
 * @since 2021-03-06
 */
public interface ReportService extends IService<Report> {

    Page<Map<String, Object>> productPriceData(ProductPriceReportDTO productPriceReportDTO);
}
