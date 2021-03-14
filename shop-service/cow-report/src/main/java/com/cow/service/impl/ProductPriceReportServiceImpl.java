package com.cow.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.mapper.ProductPriceReportMapper;
import com.cow.po.dto.ProductPriceReportDTO;
import com.cow.po.pojo.Report;
import com.cow.service.ProductPriceReportService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 产品价格报表
 */
@Service
public class ProductPriceReportServiceImpl extends ServiceImpl<ProductPriceReportMapper, Object> implements ProductPriceReportService {

    @Override
    public Page<Map<String, Object>> pageData(ProductPriceReportDTO productPriceReportDTO) {
        return baseMapper.pageData(productPriceReportDTO.page(), productPriceReportDTO);
    }

}
