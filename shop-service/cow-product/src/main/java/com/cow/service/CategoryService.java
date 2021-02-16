package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CategoryDTO;
import com.cow.po.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  产品分类 服务类
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
public interface CategoryService extends IService<Category> {

    /**
     * 添加
     * @param category
     */
    void add(Category category);

    /**
     * 更新
     * @param category
     */
    void update(Category category);

    /**
     * 列表数据
     * @param categoryDTO
     * @return
     */
    Page<Category> pageData(CategoryDTO categoryDTO);
}
