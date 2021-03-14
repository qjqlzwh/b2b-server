package com.cow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductPriceReportDTO;
import com.cow.po.pojo.Report;

import java.util.Map;

public interface ProductPriceReportMapper extends BaseMapper<Object> {

    Page<Map<String, Object>> pageData(Page page, ProductPriceReportDTO productPriceReportDTO);
}
