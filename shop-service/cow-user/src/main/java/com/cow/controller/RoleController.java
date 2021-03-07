package com.cow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.RoleDTO;
import com.cow.po.pojo.Role;
import com.cow.resp.R;
import com.cow.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    /**
     * 加载列表数据
     */
    @GetMapping("/list")
    public R list(RoleDTO roleDTO) {
        Page<Role> page = roleService.pageData(roleDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    public R add(@RequestBody Role role) {
        roleService.add(role);
        return R.ok();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public R update(@RequestBody Role role) {
        roleService.update(role);
        return R.ok();
    }

    /**
     * 根据id获取
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        Role role = roleService.getById(id);
        return R.ok().data(role);
    }

    /**
     * 获取所有角色
     */
    @GetMapping("/allRole")
    public R allRole() {
        List<Role> roles = roleService.list();
        return R.ok().data(roles);
    }

}

