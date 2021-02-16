package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.AreaDTO;
import com.cow.po.pojo.Area;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  地区服务类
 * </p>
 *
 * @author cow
 * @since 2021-01-23
 */
public interface AreaService extends IService<Area> {

    /**
     * 初始化地区
     * @param areas
     */
    void init(List<Area> areas);

    /**
     * 列表数据
     * @param areaDTO
     * @return
     */
    Page<Area> pageData(AreaDTO areaDTO);

    /**
     * 获取所有地区
     */
    List<Area> allArea();
}
