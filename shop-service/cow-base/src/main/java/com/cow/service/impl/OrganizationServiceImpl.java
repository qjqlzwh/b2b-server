package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mapper.OrganizationMapper;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.OrganizationDTO;
import com.cow.po.pojo.Organization;
import com.cow.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 机构服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-01-26
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    /**
     * 新增
     */
    @Override
    @Transactional
    public void add(Organization organization) {
        handleSaveOrUpdate(organization);
        organization.setDcode(UUID.randomUUID().toString());
        baseMapper.insert(organization);
    }

    /**
     * 更新
     */
    @Override
    @Transactional
    public void update(Organization organization) {
        handleSaveOrUpdate(organization);
        organization.setDcode(null);  // 设置为空，则不更新该字段
        baseMapper.updateById(organization);
    }

    private void handleSaveOrUpdate(Organization organization) {
        Assert.hasText(organization.getDname(), "机构名称不能为空！");
        // 处理上级
        if (organization.getParentId() != null) {
            Organization parent = baseMapper.selectById(organization.getParentId());
            Assert.notNull(parent, "上级机构不存在，请换一个！");
            if (StringUtils.hasText(parent.getParentIds())) {
                organization.setParentIds(parent.getParentIds() + "," + parent.getId());
            } else {
                organization.setParentIds(parent.getId().toString());
            }
            // 处理全名
            if (StringUtils.hasText(parent.getFullName())) {
                organization.setFullName(parent.getFullName() + " / " + organization.getDname());
            } else {
                organization.setFullName(organization.getDname());
            }
            if (!parent.getHasChild()) {
                parent.setHasChild(true);
                baseMapper.updateById(parent);
            }
        } else {
            organization.setFullName(organization.getDname());
            organization.setParentIds("");
        }
    }

    /**
     * 列表数据
     */
    @Override
    public Page<Organization> pageData(OrganizationDTO organizationDTO) {
        QueryWrapper<Organization> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "dname", organizationDTO.getDname());
        QwUtils.eq(qw, "dcode", organizationDTO.getDcode());
        QwUtils.eq(qw, "is_enabled", organizationDTO.getIsEnabled());
        if (qw.getExpression().getNormal().isEmpty()) {
            // 没有带条件时只查顶级
            qw.isNull("parent_id");
        }
        return baseMapper.selectPage(organizationDTO.page(), qw);
    }
}
