package com.cow.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cow.po.pojo.ProductPriceCustomerItem;
import com.cow.mapper.ProductPriceCustomerItemMapper;
import com.cow.service.ProductPriceCustomerItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品价格 - 客户明细 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
@Service
public class ProductPriceCustomerItemServiceImpl extends ServiceImpl<ProductPriceCustomerItemMapper, ProductPriceCustomerItem> implements ProductPriceCustomerItemService {

    /**
     * 根据主表id获取客户明细
     *
     * @param productPriceId
     * @return
     */
    @Override
    public List<ProductPriceCustomerItem> listByProductPriceId(Long productPriceId) {
        return baseMapper.selectList(Wrappers.<ProductPriceCustomerItem>lambdaQuery().eq(ProductPriceCustomerItem::getProductPrice, productPriceId));
    }
}
