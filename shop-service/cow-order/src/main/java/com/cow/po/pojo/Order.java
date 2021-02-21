package com.cow.po.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author cow
 * @since 2021-02-19
 */
@TableName("bs_order")
@ApiModel(value = "Order对象", description = "订单")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "订单号")
    private String sn;

    @ApiModelProperty(value = "客户（bs_customer）")
    private Long customer;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "业务员（bs_user）")
    private Long salesman;

    @ApiModelProperty(value = "业务员名称")
    private String salesmanName;

    @ApiModelProperty(value = "订单状态，0已保存 1已提交 2已审核 3已取消")
    private Integer state;

    @ApiModelProperty(value = "订单类型，0普通订单 1现金订单")
    private Integer orderType;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "收货地址（bs_customer_address）")
    private Long customerAddress;

    @ApiModelProperty(value = "收货人")
    private String shConsignee;

    @ApiModelProperty(value = "收货电话")
    private String shPhone;

    @ApiModelProperty(value = "收货省")
    private String shProvince;

    @ApiModelProperty(value = "收货市")
    private String shCity;

    @ApiModelProperty(value = "收货区")
    private String shDistrict;

    @ApiModelProperty(value = "收货详细地址")
    private String shDetailedAddress;

    @ApiModelProperty(value = "订单明细列表")
    @TableField(exist = false)
    private List<OrderItem> orderItemList;

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
        return this.sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getCustomer() {
        return this.customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesmanName() {
        return this.salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Long getSalesman() {
        return salesman;
    }

    public void setSalesman(Long salesman) {
        this.salesman = salesman;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Long customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getShConsignee() {
        return shConsignee;
    }

    public void setShConsignee(String shConsignee) {
        this.shConsignee = shConsignee;
    }

    public String getShPhone() {
        return shPhone;
    }

    public void setShPhone(String shPhone) {
        this.shPhone = shPhone;
    }

    public String getShProvince() {
        return shProvince;
    }

    public void setShProvince(String shProvince) {
        this.shProvince = shProvince;
    }

    public String getShCity() {
        return shCity;
    }

    public void setShCity(String shCity) {
        this.shCity = shCity;
    }

    public String getShDistrict() {
        return shDistrict;
    }

    public void setShDistrict(String shDistrict) {
        this.shDistrict = shDistrict;
    }

    public String getShDetailedAddress() {
        return shDetailedAddress;
    }

    public void setShDetailedAddress(String shDetailedAddress) {
        this.shDetailedAddress = shDetailedAddress;
    }

    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
