package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.TaskInfoLogDTO;
import com.cow.po.pojo.TaskConfig;
import com.cow.po.pojo.TaskInfoLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 任务日志历史表（报文） 服务类
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
public interface TaskInfoLogService extends IService<TaskInfoLog> {

    Page<TaskConfig> pageData(TaskInfoLogDTO taskInfoLogDTO);
}
