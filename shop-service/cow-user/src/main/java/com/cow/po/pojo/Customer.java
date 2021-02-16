package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.ArrayList;
import java.util.Date;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 客户
 * </p>
 *
 * @author cow
 * @since 2021-02-01
 */
@TableName("bs_customer")
@ApiModel(value = "Customer对象", description = "客户")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "关联用户表（bs_user）")
    private Long userId;

    @ApiModelProperty(value = "机构（bs_organization）")
    private Long organization;

    @ApiModelProperty(value = "客户名称")
    private String dname;

    @ApiModelProperty(value = "客户编码")
    private String dcode;

    @ApiModelProperty(value = "营业执照号")
    private String licenseNo;

    @ApiModelProperty(value = "是否启用")
    private Boolean isEnabled;

    @ApiModelProperty(value = "业务员（bs_user）")
    private Long salesman;

    @TableField(exist = false)
    @ApiModelProperty(value = "客户收货地址")
    private List<CustomerAddress> customerAddressList = new ArrayList<>();

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrganization() {
        return organization;
    }

    public void setOrganization(Long organization) {
        this.organization = organization;
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

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Long getSalesman() {
        return salesman;
    }

    public void setSalesman(Long salesman) {
        this.salesman = salesman;
    }

    public List<CustomerAddress> getCustomerAddressList() {
        return this.customerAddressList;
    }

    public void setCustomerAddressList(List<CustomerAddress> customerAddressList) {
        this.customerAddressList = customerAddressList;
    }
}
