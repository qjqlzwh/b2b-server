package com.cow.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.AreaDTO;
import com.cow.po.pojo.Area;
import com.cow.mapper.AreaMapper;
import com.cow.service.AreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-01-23
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {


    /**
     * 初始化地区
     */
    @Override
    @Transactional
    public void init(List<Area> areas) {
        System.out.println("地区初始化开始时间：" + DateUtil.formatDateTime(new Date()));
        this.saveBatch(areas);
        System.out.println("地区初始化结束时间：" + DateUtil.formatDateTime(new Date()));
    }

    /**
     * 列表数据
     */
    @Override
    public Page<Area> pageData(AreaDTO areaDTO) {
        Boolean hasChild = true;
        QueryWrapper<Area> qw = new QueryWrapper<>();
        QwUtils.like(qw, "dname", areaDTO.getDname());
        QwUtils.like(qw, "full_name", areaDTO.getFullName());
        if (qw.getExpression().getNormal().isEmpty()) {
            qw.isNull("parent_id");
        } else {
            hasChild = false;
        }
        Page<Area> page = baseMapper.selectPage(areaDTO.page(), qw);
        if (hasChild) {
            page.getRecords().forEach(area -> area.setHasChild(true));
        }
        return page;
    }

    /**
     * 获取所有地区
     */
    @Override
//    @Cacheable(cacheNames = "area", key = "#root.methodName")
//    @Cacheable(value = "area", key = "'allArea'")
    @Cached(name = "base:area:", key = "'allArea'", expire = 86400)
    public List<Area> allArea() {
        // 获取所有省级
        List<Area> areas = baseMapper.selectList(Wrappers.<Area>lambdaQuery().isNull(Area::getParentId));
        for (Area province : areas) {
            // 获取所有市级
            List<Area> cityAreas = baseMapper.selectList(Wrappers.<Area>lambdaQuery().eq(Area::getParentId, province.getId()));
            province.setChildArea(cityAreas);
            for (Area city : cityAreas) {
                // 获取所有区级
                List<Area> districtAreas = baseMapper.selectList(Wrappers.<Area>lambdaQuery().eq(Area::getParentId, city.getId()));
                if (CollectionUtils.isNotEmpty(districtAreas)) {
                    city.setChildArea(districtAreas);
                }
            }
        }
        return areas;
    }

}
