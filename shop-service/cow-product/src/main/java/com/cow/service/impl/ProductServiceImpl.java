package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.ProductDTO;
import com.cow.po.pojo.Product;
import com.cow.mapper.ProductMapper;
import com.cow.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  产品服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    /**
     * 增加产品
     *
     * @param product
     */
    @Override
    public void add(Product product) {

    }

    /**
     * 更新产品
     *
     * @param product
     */
    @Override
    public void update(Product product) {

    }

    /**
     * 列表数据
     *
     * @param productDTO
     * @return
     */
    @Override
    public Page<Product> pageData(ProductDTO productDTO) {
        QueryWrapper<Product> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "dcode", productDTO.getDcode());
        QwUtils.eq(qw, "dname", productDTO.getDname());
        QwUtils.eq(qw, "model", productDTO.getModel());
        QwUtils.eq(qw, "is_shelves", productDTO.getIsShelves());
        qw.orderByDesc("id");
        Page<Product> pageData = baseMapper.selectPage(productDTO.page(), qw);
        return pageData;
    }
}
