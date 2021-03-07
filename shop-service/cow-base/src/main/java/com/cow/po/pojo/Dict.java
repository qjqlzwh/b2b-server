package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  字典表
 * </p>
 *
 * @author cow
 * @since 2020-12-24
 */
@TableName("bs_dict")
@ApiModel(value = "Dict对象", description = "字典表")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "编码")
    private String dcode;

    @ApiModelProperty(value = "词汇名")
    private String dname;

    @ApiModelProperty(value = "词汇值（同一类型字典的值不能重复）")
    private Integer dvalue;

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

    @TableField(exist = false)
    @ApiModelProperty(value = "子项")
    private List<Dict> childDict = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
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

    public Integer getDvalue() {
        return this.dvalue;
    }

    public void setDvalue(Integer dvalue) {
        this.dvalue = dvalue;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSecondCode() {
        return this.secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode;
    }

    public List<Dict> getChildDict() {
        return this.childDict;
    }

    public void setChildDict(List<Dict> childDict) {
        this.childDict = childDict;
    }
}
