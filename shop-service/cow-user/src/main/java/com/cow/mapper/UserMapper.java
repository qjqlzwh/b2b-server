package com.cow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cow.po.dto.CustomerDTO;
import com.cow.po.dto.UserDTO;
import com.cow.po.pojo.User;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author row
 * @since 2020-12-24
 */
public interface UserMapper extends BaseMapper<User> {

    Page<Map<String, Object>> pageData(Page page, UserDTO userDTO);

}
