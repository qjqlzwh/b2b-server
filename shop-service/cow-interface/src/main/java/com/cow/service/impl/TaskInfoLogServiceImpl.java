package com.cow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.TaskInfoLogDTO;
import com.cow.po.pojo.TaskConfig;
import com.cow.po.pojo.TaskInfoLog;
import com.cow.mapper.TaskInfoLogMapper;
import com.cow.service.TaskInfoLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务日志历史表（报文） 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@Service
public class TaskInfoLogServiceImpl extends ServiceImpl<TaskInfoLogMapper, TaskInfoLog> implements TaskInfoLogService {

    @Override
    public Page<TaskConfig> pageData(TaskInfoLogDTO taskInfoLogDTO) {
        QueryWrapper<TaskInfoLog> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "sn", taskInfoLogDTO.getSn());
        QwUtils.eq(qw, "type", taskInfoLogDTO.getType());
        QwUtils.eq(qw, "doc_type", taskInfoLogDTO.getDocType());
        QwUtils.eq(qw, "resp_result", taskInfoLogDTO.getRespResult());
        return baseMapper.selectPage(taskInfoLogDTO.page(), qw);
    }
}
