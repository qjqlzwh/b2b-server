package com.cow.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CategoryDTO;
import com.cow.po.dto.ProductDTO;
import com.cow.po.pojo.Category;
import com.cow.po.pojo.Product;
import com.cow.resp.R;
import com.cow.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  产品分类控制器
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody Category category) {
        categoryService.add(category);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody Category category) {
        categoryService.update(category);
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
        Category category = categoryService.getById(id);
        return R.ok().data(category);
    }

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(CategoryDTO categoryDTO) {
        Page<Category> page = categoryService.pageData(categoryDTO);
        return R.ok().data(page);
    }

    /**
     * 加载子数据
     */
    @GetMapping("/getChild/{parentId}")
    public R getChild(@PathVariable("parentId") Long parentId) {
        List<Category> childList = categoryService.list(Wrappers.<Category>lambdaQuery().eq(Category::getParentId, parentId));
        return R.ok().data(childList);
    }

}

