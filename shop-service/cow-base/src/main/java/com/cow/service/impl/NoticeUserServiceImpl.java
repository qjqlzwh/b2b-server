package com.cow.service.impl;

import com.cow.po.pojo.NoticeUser;
import com.cow.mapper.NoticeUserMapper;
import com.cow.service.NoticeUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知用户 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-09
 */
@Service
public class NoticeUserServiceImpl extends ServiceImpl<NoticeUserMapper, NoticeUser> implements NoticeUserService {

}
