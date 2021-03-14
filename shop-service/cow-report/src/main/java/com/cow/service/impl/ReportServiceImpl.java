package com.cow.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductPriceReportDTO;
import com.cow.po.pojo.Report;
import com.cow.mapper.ReportMapper;
import com.cow.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-06
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Override
    public Page<Map<String, Object>> productPriceData(ProductPriceReportDTO productPriceReportDTO) {
        return baseMapper.productPriceData(productPriceReportDTO.page(), productPriceReportDTO);
    }
}
