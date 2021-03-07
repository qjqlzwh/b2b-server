package com.cow.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.jwt.JwtUtils;
import com.cow.po.dto.UserDTO;
import com.cow.po.pojo.Role;
import com.cow.po.pojo.UserRole;
import com.cow.resp.R;
import com.cow.service.UserRoleService;
import com.cow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cow.po.pojo.User;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cow
 * @since 2020-12-24
 */
//@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 登录
     * @return 登录成功将 token 返回
     */
    @PostMapping("/login")
    public R<Map> login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return R.ok().data(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public R<User> info(String token) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        //查询数据库根据用户id获取用户信息
        User user = userService.getById(userId);
        return R.ok().data(user);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public R register(String username, String password) {
        userService.register(username, password);
        return R.ok().msg("注册成功");
    }

    /**
     * 新增用户，此入口只允许增加内部用户，企业用户不能由此增加
     */
    @PostMapping("/add")
    public R add(@RequestBody User user) {
        userService.add(user);
        return R.ok();
    }

    /**
     * 更新用户
     */
    @PostMapping("/update")
    public R update(@RequestBody User user) {
        userService.update(user);
        return R.ok();
    }

    /**
     * 根据id获取
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        List<UserRole> userRoles = userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUser, id));
        Set<Long> roles = new LinkedHashSet<>();
        for (UserRole item : userRoles) {
            roles.add(item.getRole());
        }
        user.setRoleList(roles);
        user.setPassword("");
        return R.ok().data(user);
    }

    /**
     * 获取用户数据 - 分页
     * @return
     */
    @GetMapping("/list")
    public R list(UserDTO userDTO) {
        Page<User> userList = userService.userPage(userDTO);
        return R.ok().data(userList);
    }

}

