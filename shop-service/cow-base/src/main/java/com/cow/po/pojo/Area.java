package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 地区
 * </p>
 *
 * @author cow
 * @since 2021-01-23
 */
@TableName("bs_area")
@ApiModel(value = "Area对象", description = "地区")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "地区编码")
    private String dcode;

    @ApiModelProperty(value = "地区名称")
    private String dname;

    @ApiModelProperty(value = "地区全名")
    private String fullName;

    @ApiModelProperty(value = "简称")
    private String simpleName;

    @ApiModelProperty(value = "上级")
    private Long parentId;

    @ApiModelProperty(value = "上级编码")
    private String parentCode;

    @ApiModelProperty(value = "是否存在子级")
    @TableField(exist = false)
    private Boolean hasChild;

    @ApiModelProperty(value = "子级地区")
    @TableField(exist = false)
    private List<Area> childArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Boolean getHasChild() {
        return this.hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    public List<Area> getChildArea() {
        return this.childArea;
    }

    public void setChildArea(List<Area> childArea) {
        this.childArea = childArea;
    }
}
