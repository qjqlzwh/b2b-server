package com.cow.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.ProductDTO;
import com.cow.po.pojo.Category;
import com.cow.po.pojo.Product;
import com.cow.resp.R;
import com.cow.service.CategoryService;
import com.cow.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  产品控制器
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(ProductDTO productDTO) {
        Page<Product> page = productService.pageData(productDTO);
        return R.ok().data(page);
    }

    /**
     * 加载弹框列表数据
     */
    @GetMapping("/popList")
    public R popList(ProductDTO productDTO) {
        Page<Product> page = productService.pageData(productDTO);
        return R.ok().data(page);
    }

    /**
     * 加载弹框列表数据(带价格产品，如订单选产品)
     */
    @GetMapping("/popPriceList")
    public R popPriceList(ProductDTO productDTO) {
        Page<Map<String, Object>> page = productService.popPriceList(productDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody Product product) {
        productService.add(product);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody Product product) {
        productService.update(product);
        return R.ok();
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Product product = productService.getById(id);
        Map<String, Object> productMap = BeanUtil.beanToMap(product);
        if (product.getCategory() != null) {
            Category category = categoryService.getById(product.getCategory());
            productMap.put("categoryName", category.getDname());
        }
        return R.ok().data(productMap);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Product info(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

}

