package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.OrganizationDTO;
import com.cow.po.pojo.Dict;
import com.cow.po.pojo.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cow
 * @since 2021-01-26
 */
public interface OrganizationService extends IService<Organization> {

    /**
     * 新增
     */
    void add(Organization organization);

    /**
     * 更新
     */
    void update(Organization organization);

    /**
     * 列表数据
     */
    Page<Organization> pageData(OrganizationDTO organizationDTO);
}
