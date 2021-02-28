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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

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
    @Transactional
    public void add(Product product) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        product.setDcode(String.valueOf(System.currentTimeMillis()));
        handleSaveOrUpdate(product);
        baseMapper.insert(product);
    }

    /**
     * 更新产品
     *
     * @param product
     */
    @Override
    @Transactional
    public void update(Product product) {
        handleSaveOrUpdate(product);
        baseMapper.updateById(product);
    }

    private void handleSaveOrUpdate(Product product) {
        Assert.hasText(product.getDname(), "产品名称不能为空！");
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

    /**
     * 加载弹框列表数据(带价格产品，如订单选产品)
     *
     * @param productDTO
     * @return
     */
    @Override
    public Page<Map<String, Object>> popPriceList(ProductDTO productDTO) {
        productDTO.setStartTime(new Date());
        productDTO.setEndTime(new Date());
        return baseMapper.popPriceList(productDTO.page(), productDTO);
    }
}
