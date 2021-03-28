package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.TaskConfigDTO;
import com.cow.po.pojo.TaskConfig;
import com.cow.mapper.TaskConfigMapper;
import com.cow.service.TaskConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * <p>
 * 任务配置 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@Service
public class TaskConfigServiceImpl extends ServiceImpl<TaskConfigMapper, TaskConfig> implements TaskConfigService {

    /**
     * 列表数据
     *
     * @param taskConfigDTO
     * @return
     */
    @Override
    public Page<TaskConfig> pageData(TaskConfigDTO taskConfigDTO) {
        QueryWrapper<TaskConfig> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "type", taskConfigDTO.getType());
        QwUtils.like(qw, "dname", taskConfigDTO.getDname());
        QwUtils.eq(qw, "is_enabled", taskConfigDTO.getIsEnabled());
        return baseMapper.selectPage(taskConfigDTO.page(), qw);
    }

    /**
     * 新增
     *
     * @param taskConfig
     */
    @Override
    @Transactional
    public void add(TaskConfig taskConfig) {
        handleSaveOrUpdate(taskConfig);
        baseMapper.insert(taskConfig);
    }

    /**
     * 更新
     *
     * @param taskConfig
     */
    @Override
    @Transactional
    public void update(TaskConfig taskConfig) {
        handleSaveOrUpdate(taskConfig);
        baseMapper.updateById(taskConfig);
    }

    private void handleSaveOrUpdate(TaskConfig taskConfig) {
        Assert.notNull(taskConfig.getType(), "任务类型不能为空");
        Assert.hasText(taskConfig.getDname(), "接口名称不能为空");
        Integer count = null;
        if (taskConfig.getId() == null) {
            count = baseMapper.selectCount(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getType, taskConfig.getType()));
        } else {
            count = baseMapper.selectCount(
                    Wrappers.<TaskConfig>lambdaQuery()
                            .eq(TaskConfig::getType, taskConfig.getType())
                            .ne(TaskConfig::getId, taskConfig.getId()));
        }
        Assert.isTrue(count == 0, "任务类型已存在，请换一个");
    }
}
