package com.cow.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.UserDTO;
import com.cow.exception.ExceptionUtils;
import com.cow.jwt.JwtUtils;
import com.cow.po.pojo.User;
import com.cow.mapper.UserMapper;
import com.cow.po.pojo.UserRole;
import com.cow.service.UserRoleService;
import com.cow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  用户 - 服务实现类
 * </p>
 *
 * @author cow
 * @since 2020-12-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 登录
     * @return
     */
    @Override
    public String login(String username, String password) {
        // TODO 加入jwt处理、密码错误不超过5次

        Assert.hasText(username, "用户名不能为空！");
        Assert.hasText(password, "密码不能为空！");

        // 判断用户是否存在
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        Assert.notNull(user, "用户不存在！");

        Assert.isTrue(user.getIsEnabled(), "用户已被禁用！");

        // 校验密码  明文密码 + uuid
        String md5pre = password + user.getUniqueIdentifier();
        String md5after = DigestUtils.md5DigestAsHex(md5pre.getBytes());
        if (!md5after.equals(user.getPassword())) {
            ExceptionUtils.throwRowException("密码错误");
        }

//        JwtUtils.getJwtToken(user.getId().toString(), user.getUsername());

        // redis
//        String token = IdUtil.fastSimpleUUID();
//        redisTemplate.opsForValue().set(RedisGroup.LOGIN + token, user, RedisExpire.LOGIN_TIME);

        // JWT方案
        String token = JwtUtils.generateJwtToken(user.getId().toString(), user.getUsername());
        return token;
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     */
    @Override
    @Transactional
    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        verifyUsernamePw(user);

        user.setIsEnabled(true);
        baseMapper.insert(user);
    }

    /**
     * 用户名、密码校验
     * @param user
     */
    private void verifyUsernamePw(User user) {
        String username = user.getUsername().trim();
        String password = user.getPassword();
        Assert.hasText(username, "用户名不能为空！");
        Assert.hasText(password, "密码不能为空！");

        // 判断用户是否存在
        User eUser = baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        Assert.isNull(eUser, "用户已经存在！");
        if (password.length() < 6) {
            ExceptionUtils.throwRowException("密码长度不能少于6位！");
        }

        String uuid = IdUtil.fastSimpleUUID();
        String pwd = password + uuid;

        user.setUsername(username);
        user.setUniqueIdentifier(uuid);
        user.setPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
    }

    /**
     * 分页查用户数据
     *
     * @param userDTO
     * @return
     */
    @Override
    public Page<User> userPage(UserDTO userDTO) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        QwUtils.eq(qw, "username", userDTO.getUsername());
        QwUtils.eq(qw, "is_enabled", userDTO.getIsEnabled());
        QwUtils.eq(qw, "user_type", userDTO.getUserType());
        // 查询过滤password字段
        qw.select(User.class, i -> !i.getProperty().equals("password"));

        return baseMapper.selectPage(userDTO.page(), qw);
    }

    /**
     * 增加用户
     */
    @Override
    @Transactional
    public void add(User user) {
        verifyUsernamePw(user);
        baseMapper.insert(user);
    }

    /**
     * 更新用户
     */
    @Override
    @Transactional
    public void update(User user) {
        User oldUser = baseMapper.selectById(user.getId());
        // 判断用户是否存在
        if (!oldUser.getUsername().equals(user.getUsername())) {
            User eUser = baseMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
            Assert.isNull(eUser, "用户名已经存在！");
            oldUser.setUsername(user.getUsername());
        }
        if (StringUtils.hasText(user.getPassword())) {
            if (user.getPassword().length() < 6) {
                ExceptionUtils.throwRowException("密码长度不能少于6位！");
            }
            String pwd = user.getPassword() + oldUser.getUniqueIdentifier();
            oldUser.setPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
        }
        oldUser.setIsEnabled(user.getIsEnabled());
        oldUser.setPhone(user.getPhone());
        oldUser.setEmail(user.getEmail());

        // 处理角色
        userRoleService.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUser, user.getId()));
        if (!CollectionUtils.isEmpty(user.getRoleList())) {
            List<UserRole> userRoles = new ArrayList<>();
            for (Long role : user.getRoleList()) {
                UserRole userRole = new UserRole();
                userRole.setUser(user.getId());
                userRole.setRole(role);
                userRoles.add(userRole);
            }
            userRoleService.saveBatch(userRoles);
        }

        baseMapper.updateById(oldUser);
    }

    /**
     * 测试分页导出
     *
     * @param userDTO
     * @return
     */
    @Override
    public Page<Map<String, Object>> testPageExport(UserDTO userDTO) {
        QueryWrapper<User> qw = new QueryWrapper<>();
//        QwUtils.eq(qw, "username", "root");
        return baseMapper.selectMapsPage(userDTO.page(), qw);
//        return baseMapper.pageData(userDTO.page(), userDTO);
    }

}
