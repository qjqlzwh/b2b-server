package com.cow.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.UserDTO;
import com.cow.po.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cow.po.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cow
 * @since 2020-12-24
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 分页查用户数据
     * @param userDTO
     * @return
     */
    Page<User> userPage(UserDTO userDTO);

    /**
     * 增加用户
     */
    void add(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);
}
