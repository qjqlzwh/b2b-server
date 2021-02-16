package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户
 */
public class UserDTO extends PageDTO {

    @ApiModelProperty(value = "用户名（登录账号）")
    private String username;

    @ApiModelProperty(value = "false禁用  true启用")
    private Boolean isEnabled;

    @ApiModelProperty(value = "0内部用户  1企业用户")
    private Integer userType;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    public Integer getUserType() {
        return this.userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}