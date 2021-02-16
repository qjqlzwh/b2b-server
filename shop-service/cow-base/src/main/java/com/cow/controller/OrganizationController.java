package com.cow.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.OrganizationDTO;
import com.cow.po.pojo.Organization;
import com.cow.resp.R;
import com.cow.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 机构
 * </p>
 *
 * @author cow
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody Organization organization) {
        organizationService.add(organization);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody Organization organization) {
        organizationService.update(organization);
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
//        Map<String, Object> org = organizationService.getMap(Wrappers.<Organization>lambdaQuery().eq(Organization::getId, id));
        Organization organization = organizationService.getById(id);
        // 对象转Map
        Map<String, Object> orgMap = BeanUtil.beanToMap(organization);
        if (organization.getParentId() != null) {
            Organization parent = organizationService.getById(organization.getParentId());
            orgMap.put("parentName", parent.getDname());
        }
        return R.ok().data(orgMap);
    }

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(OrganizationDTO organizationDTO) {
        Page<Organization> page = organizationService.pageData(organizationDTO);
        return R.ok().data(page);
    }

    /**
     * 加载子列表数据
     */
    @GetMapping("/getChild/{parentId}")
    public R getChild(@PathVariable("parentId") Long parentId) {
        List<Organization> childList = organizationService.list(Wrappers.<Organization>lambdaQuery().eq(Organization::getParentId, parentId));
        return R.ok().data(childList);
    }

    @GetMapping("/info/{id}")
    public Organization info(@PathVariable("id") Long id) {
        return organizationService.getById(id);
    }
}

