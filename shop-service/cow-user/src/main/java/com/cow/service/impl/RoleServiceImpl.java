package com.cow.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.RoleDTO;
import com.cow.po.pojo.Role;
import com.cow.mapper.RoleMapper;
import com.cow.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 列表数据
     *
     * @param roleDTO
     * @return
     */
    @Override
    public Page<Role> pageData(RoleDTO roleDTO) {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "dname", roleDTO.getDname());
        QwUtils.eq(qw, "dcode", roleDTO.getDcode());
        QwUtils.eq(qw, "is_enabled", roleDTO.getIsEnabled());
        return baseMapper.selectPage(roleDTO.page(), qw);
    }

    /**
     * 添加
     *
     * @param role
     */
    @Override
    @Transactional
    public void add(Role role) {
        role.setDcode(IdUtil.simpleUUID());
        handleSaveOrUpdate(role);
        baseMapper.insert(role);
    }

    /**
     * 更新
     *
     * @param role
     */
    @Override
    @Transactional
    public void update(Role role) {
        handleSaveOrUpdate(role);
        baseMapper.updateById(role);
    }

    private void handleSaveOrUpdate(Role role) {
        Assert.hasText(role.getDname(), "角色名称不能为空！");

        Integer count;
        if (role.getId() == null) {
            count = baseMapper.selectCount(Wrappers.<Role>lambdaQuery().eq(Role::getDname, role.getDname()));
        } else {
            count = baseMapper.selectCount(
                    Wrappers.<Role>lambdaQuery()
                            .eq(Role::getDname, role.getDname())
                            .ne(Role::getId, role.getId()));
        }
        Assert.isTrue(count == 0, "角色名称已经存在，请换一个名称！");
    }
}
