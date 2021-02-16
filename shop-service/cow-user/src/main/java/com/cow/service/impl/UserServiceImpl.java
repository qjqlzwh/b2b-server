package com.cow.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.mybatis.QwUtils;
import com.cow.po.dto.UserDTO;
import com.cow.exception.ExceptionUtils;
import com.cow.jwt.JwtUtils;
import com.cow.po.pojo.User;
import com.cow.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cow.po.vo.UserVo;
import com.cow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

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

        JwtUtils.getJwtToken(user.getId().toString(), user.getUsername());

        // redis
//        String token = IdUtil.fastSimpleUUID();
//        redisTemplate.opsForValue().set(RedisGroup.LOGIN + token, user, RedisExpire.LOGIN_TIME);

        // JWT方案
        String token = JwtUtils.getJwtToken(user.getId().toString(), user.getUsername());
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
////        Wrappers.lambdaQuery().
//        LambdaQueryWrapper<User> qw = Wrappers.lambdaQuery(User.class);
////        User::getUsername
//
////        QwUtils.eq(qw, User::getUsername, userDTO.getUsername());
//        qw.eq(User::getUsername, userDTO.getUsername());
////        LambdaQueryWrapper<User> eq = userLambdaQueryWrapper.eq(User::getIsEnabled, true);
////        userLambdaQueryWrapper.eq

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
        if (!oldUser.equals(user.getUsername())) {
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
        baseMapper.updateById(oldUser);
    }

    public static void main(String[] args) {
//        String uuid = IdUtil.simpleUUID();
//        String uuid2 = IdUtil.fastSimpleUUID();
        String password = "123456";
//        String md1 = DigestUtils.md5DigestAsHex(password.getBytes());
//        String md2 = SecureUtil.md5(password);
//        System.out.println(md1);
//        System.out.println(md2);
//        System.out.println(uuid);
//        System.out.println(uuid2);
//        System.out.println(SecureUtil.md5(password+uuid2));
//        System.out.println(password.length());
        System.out.println(DigestUtils.md5DigestAsHex(("123456" + "a3df43feed7f4cd2bbec8008f4b40391").getBytes()));
    }
}
