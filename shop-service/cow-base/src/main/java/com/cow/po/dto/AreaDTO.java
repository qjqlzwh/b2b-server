package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

public class AreaDTO extends PageDTO {

    @ApiModelProperty(value = "地区名称")
    private String dname;

    @ApiModelProperty(value = "地区全名")
    private String fullName;

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
