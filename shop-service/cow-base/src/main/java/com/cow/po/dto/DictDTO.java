package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

public class DictDTO extends PageDTO {

    private Long id;

    @ApiModelProperty(value = "编码")
    private String dcode;

    @ApiModelProperty(value = "词汇名")
    private String dname;

    @ApiModelProperty(value = "上级id")
    private Long parentId;

    @ApiModelProperty(value = "值越小越靠前")
    private Integer sort;

    @ApiModelProperty(value = "是否启用 0禁用  1启用")
    private Boolean isEnabled;

    @ApiModelProperty(value = "二级编码")
    private String secondCode;

    @ApiModelProperty(value = "备注")
    private String memo;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    public String getSecondCode() {
        return this.secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
