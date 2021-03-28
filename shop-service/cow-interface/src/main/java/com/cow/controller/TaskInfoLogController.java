package com.cow.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.TaskConfigDTO;
import com.cow.po.dto.TaskInfoLogDTO;
import com.cow.po.pojo.TaskConfig;
import com.cow.resp.R;
import com.cow.service.TaskInfoLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 任务日志历史表（报文） 前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@RestController
@RequestMapping("/task-info-log")
public class TaskInfoLogController {

    @Autowired
    private TaskInfoLogService taskInfoLogService;

    /**
     * 加载列表数据
     * @param taskInfoLogDTO
     * @return
     */
    @GetMapping("/list")
    public R list(TaskInfoLogDTO taskInfoLogDTO) {
        Page<TaskConfig> page = taskInfoLogService.pageData(taskInfoLogDTO);
        return R.ok().data(page);
    }
}

