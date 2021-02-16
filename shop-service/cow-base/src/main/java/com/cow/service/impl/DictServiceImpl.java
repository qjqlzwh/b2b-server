package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.exception.ExceptionUtils;
import com.cow.mapper.DictMapper;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.DictDTO;
import com.cow.po.pojo.Dict;
import com.cow.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cow
 * @since 2020-12-24
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    @Transactional
    public void add(Dict dict) {
        Assert.hasText(dict.getDcode(), "顶级词汇编码不能为空！");
        // 父级编码校重
        Integer count = baseMapper.selectCount(Wrappers.<Dict>lambdaQuery().eq(Dict::getDcode, dict.getDcode()));
        if (count != null && count > 0) {
            ExceptionUtils.throwRowException("顶级词汇编码已经存在，请换一个！");
        }
        // 父级词汇名称校重
        Integer countN = baseMapper.selectCount(Wrappers.<Dict>lambdaQuery().eq(Dict::getDname, dict.getDname()));
        if (countN != null && countN > 0) {
            ExceptionUtils.throwRowException("顶级词汇名称已经存在，请换一个！");
        }
        baseMapper.insert(dict);
        List<Dict> dictList = new ArrayList<>();
        for (Dict item : dict.getChildDict()) {
            Dict dictItem = new Dict();
            BeanUtils.copyProperties(item, dictItem);
            dictItem.setParentId(dict.getId());
            dictItem.setDcode(dict.getDcode());
            dictList.add(dictItem);
        }
        // 批量保存
        this.saveBatch(dictList);
    }

    /**
     * 更新
     */
    @Override
    @Transactional
    public void update(Dict dict) {
        Assert.notNull(dict.getId(), "id不能为空！");
        Dict existDict = baseMapper.selectById(dict.getId());
        Assert.notNull(existDict, "根据id找不到该词汇！");
        Assert.isTrue(dict.getDcode().equals(existDict.getDcode()), "不能修改顶级编码！");
        baseMapper.updateById(dict);
        // 处理子项
        for (Dict item : dict.getChildDict()) {
            item.setParentId(dict.getId());
            item.setDcode(dict.getDcode());
            this.saveOrUpdate(item);
        }
    }

    /**
     * 分页数据
     */
    @Override
    public Page<Dict> pageData(DictDTO dictDTO) {
        // 获取顶级字典
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "dcode", dictDTO.getDcode());
        QwUtils.eq(qw, "dname", dictDTO.getDname());
        QwUtils.eq(qw, "is_enabled", dictDTO.getIsEnabled());
        qw.isNull("parent_id");
        qw.orderByDesc("id");
        Page<Dict> dictPage = baseMapper.selectPage(dictDTO.page(), qw);
        // 获取子级
//        for (Dict dict : dictPage.getRecords()) {
//            List<Dict> childList = baseMapper.selectList(Wrappers.<Dict>lambdaQuery().eq(Dict::getParentId, dict.getId()));
//            dict.setChildDict(childList);
//        }
        return dictPage;
    }

}
