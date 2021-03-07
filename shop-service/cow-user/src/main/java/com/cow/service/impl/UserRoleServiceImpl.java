package com.cow.service.impl;

import com.cow.po.pojo.UserRole;
import com.cow.mapper.UserRoleMapper;
import com.cow.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author cow
 * @since 2021-03-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
