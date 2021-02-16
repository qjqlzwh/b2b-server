package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

public class ParameterDTO extends PageDTO {

    @ApiModelProperty(value = "参数编码")
    private String dcode;

    @ApiModelProperty(value = "参数名称")
    private String dname;

    @ApiModelProperty(value = "参数值")
    private String dvalue;

    @ApiModelProperty(value = "是否启用")
    private Boolean isEnabled;

    public String getDcode() {
        return this.dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDvalue() {
        return this.dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }
}
