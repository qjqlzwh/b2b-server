package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单
 */
public class OrderDTO extends PageDTO {

    @ApiModelProperty(value = "业务员（bs_user）")
    private Long salesman;

    @ApiModelProperty(value = "订单状态")
    private Integer state;

    @ApiModelProperty(value = "订单类型")
    private Integer orderType;

    public Long getSalesman() {
        return this.salesman;
    }

    public void setSalesman(Long salesman) {
        this.salesman = salesman;
    }

    public Integer getState() {
        return this.state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrderType() {
        return this.orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
