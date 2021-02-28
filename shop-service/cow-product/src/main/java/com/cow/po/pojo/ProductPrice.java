package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 产品价格
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
@TableName("bs_product_price")
@ApiModel(value = "ProductPrice对象", description = "产品价格")
public class ProductPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "单号")
    private String sn;

    @ApiModelProperty(value = "价目表名称")
    private String dname;

    @ApiModelProperty(value = "单据状态（0已保存 1审核中 2已审核 3已取消）")
    private Integer state;

    @ApiModelProperty(value = "所属机构（bs_organization）")
    private Long organization;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "客户明细")
    @TableField(exist = false)
    private List<ProductPriceCustomerItem> customerItem;

    @ApiModelProperty(value = "产品明细")
    @TableField(exist = false)
    private List<ProductPriceGoodsItem> productItem;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getOrganization() {
        return organization;
    }

    public void setOrganization(Long organization) {
        this.organization = organization;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<ProductPriceCustomerItem> getCustomerItem() {
        return this.customerItem;
    }

    public void setCustomerItem(List<ProductPriceCustomerItem> customerItem) {
        this.customerItem = customerItem;
    }

    public List<ProductPriceGoodsItem> getProductItem() {
        return this.productItem;
    }

    public void setProductItem(List<ProductPriceGoodsItem> productItem) {
        this.productItem = productItem;
    }
}
