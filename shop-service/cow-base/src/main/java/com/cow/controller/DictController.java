package com.cow.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.DictDTO;
import com.cow.po.pojo.Dict;
import com.cow.resp.R;
import com.cow.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  字典
 * </p>
 *
 * @author cow
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * 新增
     * @param dict
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody Dict dict) {
        dictService.add(dict);
        return R.ok();
    }

    /**
     * 更新
     * @param dict
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody Dict dict) {
        dictService.update(dict);
        return R.ok();
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Dict dict = dictService.getById(id);
        List<Dict> childList = dictService.list(Wrappers.<Dict>lambdaQuery().eq(Dict::getParentId, id));
        dict.setChildDict(childList);
        return R.ok().data(dict);
    }

    /**
     * 加载列表数据
     * @param dictDTO
     * @return
     */
    @GetMapping("/list")
    public R list(DictDTO dictDTO) {
        Page<Dict> page = dictService.pageData(dictDTO);
        return R.ok().data(page);
    }

    /**
     * 加载子列表数据
     */
    @GetMapping("/getChild/{parentId}")
    public R getChild(@PathVariable("parentId") Long parentId) {
        List<Dict> childList = dictService.list(Wrappers.<Dict>lambdaQuery().eq(Dict::getParentId, parentId));
//        Assert.isTrue(!CollectionUtils.isEmpty(childList), "该词汇没有子项");
        return R.ok().data(childList);
    }

    /**
     * 根据id获取字典
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        Dict dict = dictService.getById(id);
        return R.ok().data(dict);
    }

}

