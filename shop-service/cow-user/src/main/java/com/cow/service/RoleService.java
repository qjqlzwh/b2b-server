package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.RoleDTO;
import com.cow.po.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author cow
 * @since 2021-03-05
 */
public interface RoleService extends IService<Role> {

    /**
     * 列表数据
     * @param roleDTO
     * @return
     */
    Page<Role> pageData(RoleDTO roleDTO);

    /**
     * 添加
     * @param role
     */
    void add(Role role);

    /**
     * 更新
     * @param role
     */
    void update(Role role);
}
