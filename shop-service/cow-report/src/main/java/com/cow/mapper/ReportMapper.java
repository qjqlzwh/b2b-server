package com.cow.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductPriceReportDTO;
import com.cow.po.pojo.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cow
 * @since 2021-03-06
 */
public interface ReportMapper extends BaseMapper<Report> {

    Page<Map<String, Object>> productPriceData(Page page, ProductPriceReportDTO productPriceReportDTO);
}
