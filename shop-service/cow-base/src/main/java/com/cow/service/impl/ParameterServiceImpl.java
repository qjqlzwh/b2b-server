package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.mapper.ParameterMapper;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.ParameterDTO;
import com.cow.po.pojo.Parameter;
import com.cow.service.ParameterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * <p>
 * 系统参数 - ServiceImpl
 * </p>
 *
 * @author cow
 * @since 2021-01-23
 */
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements ParameterService {

    /**
     * 列表数据
     */
    @Override
    public Page<Parameter> pageData(ParameterDTO parameterDTO) {
        QueryWrapper<Parameter> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "dcode", parameterDTO.getDcode());
        QwUtils.like(qw, "dname", parameterDTO.getDname());
        QwUtils.eq(qw, "is_enabled", parameterDTO.getIsEnabled());
        qw.orderByDesc("id");
        return baseMapper.selectPage(parameterDTO.page(), qw);
    }

    /**
     * 新增
     */
    @Override
    @Transactional
    public void add(Parameter parameter) {
        handleData(parameter);
        Integer codeCount = baseMapper.selectCount(Wrappers.<Parameter>lambdaQuery().eq(Parameter::getDcode, parameter.getDcode()));
        Assert.isTrue(!(codeCount > 0), "参数编码已经重复，请换一个！");
        baseMapper.insert(parameter);
    }

    /**
     * 更新
     */
    @Override
    @Transactional
    public void update(Parameter parameter) {
        handleData(parameter);
        Integer codeCount = baseMapper.selectCount(
                Wrappers.<Parameter>lambdaQuery()
                        .eq(Parameter::getDcode, parameter.getDcode())
                        .ne(Parameter::getId, parameter.getId()));
        Assert.isTrue(!(codeCount > 0), "参数编码已经重复，请换一个！");
        baseMapper.updateById(parameter);
    }

    /**
     * 根据编码查
     *
     * @param dcode 编码
     * @return
     */
    @Override
    public Parameter findByDcode(String dcode) {
        Assert.hasText(dcode, "参数编码不能为空！");
        return baseMapper.selectOne(Wrappers.<Parameter>lambdaQuery().eq(Parameter::getDcode, "dcode"));
    }

    private void handleData(Parameter parameter) {
        Assert.hasText(parameter.getDcode(), "参数编码不能为空！");
        Assert.hasText(parameter.getDname(), "参数名称不能为空！");
        Assert.hasText(parameter.getDvalue(), "参数值不能为空！");
    }

}
