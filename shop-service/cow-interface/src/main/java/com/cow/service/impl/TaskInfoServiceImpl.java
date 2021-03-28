package com.cow.service.impl;

import com.cow.po.pojo.TaskInfo;
import com.cow.mapper.TaskInfoMapper;
import com.cow.service.TaskInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务日志（报文） 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@Service
public class TaskInfoServiceImpl extends ServiceImpl<TaskInfoMapper, TaskInfo> implements TaskInfoService {

}
