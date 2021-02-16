package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

public class OrganizationDTO extends PageDTO {

    @ApiModelProperty(value = "机构名称")
    private String dname;

    @ApiModelProperty(value = "机构编码")
    private String dcode;

    @ApiModelProperty(value = "是否启用")
    private Boolean isEnabled;

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDcode() {
        return this.dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }
}
