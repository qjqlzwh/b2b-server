package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.mybatis.Rc;

public class ExtServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    /**
     * 查分页或所有数据 对象结构
     * @param page
     * @param qw
     * @return Rc<Object>
     */
    public Rc pageOrList(Page page, QueryWrapper qw) {
        if (page != null) {
            return Rc.pd(baseMapper.selectPage(page, qw));
        }
        return Rc.ld(baseMapper.selectList(qw));
    }

    /**
     * 查分页或所有数据 Map结构
     * @param page
     * @param qw
     * @return Rc<Map>
     */
    public Rc mapPageOrList(Page page, QueryWrapper qw) {
        if (page != null) {
            return Rc.pd(baseMapper.selectMapsPage(page, qw));
        }
        return Rc.ld(baseMapper.selectMaps(qw));
    }
}
