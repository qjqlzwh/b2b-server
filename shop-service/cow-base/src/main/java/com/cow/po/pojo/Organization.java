package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 机构
 * </p>
 *
 * @author cow
 * @since 2021-01-26
 */
@TableName("bs_organization")
@ApiModel(value = "Organization对象", description = "机构")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "机构名称")
    private String dname;

    @ApiModelProperty(value = "机构编码")
    private String dcode;

    @ApiModelProperty(value = "是否启用")
    private Boolean isEnabled;

    @ApiModelProperty(value = "上级机构")
    private Long parentId;

    @ApiModelProperty(value = "上级所有机构，用,号分隔")
    private String parentIds;

    @ApiModelProperty(value = "机构全名")
    private String fullName;

    @ApiModelProperty(value = "是否有子级")
    private Boolean hasChild;

    @ApiModelProperty(value = "备注")
    private String memo;


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

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public void setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", dname=" + dname +
                ", dcode=" + dcode +
                ", isEnabled=" + isEnabled +
                ", parentId=" + parentId +
                ", parentIds=" + parentIds +
                ", hasChild=" + hasChild +
                ", memo=" + memo +
                "}";
    }
}
