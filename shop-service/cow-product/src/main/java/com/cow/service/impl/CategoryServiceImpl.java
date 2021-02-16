package com.cow.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CategoryDTO;
import com.cow.po.pojo.Category;
import com.cow.mapper.CategoryMapper;
import com.cow.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    /**
     * 添加
     *
     * @param category
     */
    @Override
    @Transactional
    public void add(Category category) {
        handleSaveOrUpdate(category);
        baseMapper.insert(category);
    }

    /**
     * 更新
     *
     * @param category
     */
    @Override
    @Transactional
    public void update(Category category) {
        handleSaveOrUpdate(category);
        baseMapper.updateById(category);
    }

    private void handleSaveOrUpdate(Category category) {
        Assert.hasText(category.getDname(), "分类名称不能为空");
        Category oldCategory = null;
        if (category.getId() == null) {
            Integer count = baseMapper.selectCount(Wrappers.<Category>lambdaQuery().eq(Category::getDname, category.getDname()));
            Assert.isTrue(count <= 0, "该分类已存在！");
            category.setHasChild(false);
        } else {
            Integer count = baseMapper.selectCount(
                    Wrappers.<Category>lambdaQuery()
                            .eq(Category::getDname, category.getDname())
                            .ne(Category::getId, category.getId()));
            Assert.isTrue(count <= 0, "该分类已存在！");
            oldCategory = baseMapper.selectById(category.getId());
            Assert.isTrue(oldCategory.getDname().equals(category.getDname()), "分类名称不能修改！");
        }
        if (oldCategory != null && oldCategory.getParentId() != null) {
            // 处理旧的上级
            if (category.getParentId() == null || !oldCategory.getParentId().equals(category.getParentId())) {
                Category oldParentCategory = baseMapper.selectById(oldCategory.getParentId());
                Integer count = baseMapper.selectCount(Wrappers.<Category>lambdaQuery().eq(Category::getParentId, oldParentCategory.getId()));
                if (count == 0) {
                    oldParentCategory.setHasChild(false);
                    baseMapper.updateById(oldParentCategory);
                }
            }
        }
        // 处理新上级
        if (category.getParentId() != null) {
            Category parentCategory = baseMapper.selectById(category.getParentId());
            category.setFullName(parentCategory.getFullName() + " / " + category.getDname());
            if (StringUtils.hasText(parentCategory.getParentIds())) {
                category.setParentIds(parentCategory.getParentIds() + "," + category.getParentId());
            } else {
                category.setParentIds(category.getParentId().toString());
            }
            if (!parentCategory.getHasChild()) {
                parentCategory.setHasChild(true);
                baseMapper.updateById(parentCategory);
            }
        } else {
            category.setFullName(category.getDname());
        }
    }

    /**
     * 列表数据
     *
     * @param categoryDTO
     * @return
     */
    @Override
    public Page<Category> pageData(CategoryDTO categoryDTO) {
        return null;
    }
}
