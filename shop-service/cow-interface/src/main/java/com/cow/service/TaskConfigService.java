package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.TaskConfigDTO;
import com.cow.po.pojo.TaskConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务配置 服务类
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
public interface TaskConfigService extends IService<TaskConfig> {

    /**
     * 列表数据
     * @param taskConfigDTO
     * @return
     */
    Page<TaskConfig> pageData(TaskConfigDTO taskConfigDTO);

    /**
     * 新增
     * @param taskConfig
     */
    void add(TaskConfig taskConfig);

    /**
     * 更新
     * @param taskConfig
     */
    void update(TaskConfig taskConfig);
}
