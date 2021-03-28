package com.cow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.pojo.TaskConfig;
import com.cow.po.dto.TaskConfigDTO;
import com.cow.resp.R;
import com.cow.service.TaskConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 任务配置 前端控制器
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@RestController
@RequestMapping("/task-config")
public class TaskConfigController {

    @Autowired
    private TaskConfigService taskConfigService;

    /**
     * 加载列表数据
     * @param taskConfigDTO
     * @return
     */
    @GetMapping("/list")
    public R list(TaskConfigDTO taskConfigDTO) {
        Page<TaskConfig> page = taskConfigService.pageData(taskConfigDTO);
        return R.ok().data(page);
    }

    /**
     * 新增
     * @param taskConfig
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody TaskConfig taskConfig) {
        taskConfigService.add(taskConfig);
        return R.ok();
    }

    /**
     * 更新
     * @param taskConfig
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody TaskConfig taskConfig) {
        taskConfigService.update(taskConfig);
        return R.ok();
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        TaskConfig taskConfig = taskConfigService.getById(id);
        return R.ok().data(taskConfig);
    }

}

