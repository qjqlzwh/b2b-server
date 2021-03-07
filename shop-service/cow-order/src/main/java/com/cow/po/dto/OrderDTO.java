package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单
 */
public class OrderDTO extends PageDTO {

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

    @ApiModelProperty(value = "订单状态")
    private Integer[] state;

    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

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

    public Long getSalesman() {
        return this.salesman;
    }

    public void setSalesman(Long salesman) {
        this.salesman = salesman;
    }

    public String getSalesmanName() {
        return this.salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public Integer[] getState() {
        return this.state;
    }

    public void setState(Integer[] state) {
        this.state = state;
    }

    public Integer getOrderType() {
        return this.orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
