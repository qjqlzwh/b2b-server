package com.cow.po.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 产品价格 - 客户明细
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
@ApiModel(value = "ProductPriceCustomerItemVo对象", description = "产品价格 - 客户明细")
public class ProductPriceCustomerItemVo implements Serializable {

    private static final long serialVersionUID = -7748477393031817600L;

    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "产品价格主表（bs_product_price）")
    private Long productPrice;

    @ApiModelProperty(value = "行号")
    private Integer seq;

    @ApiModelProperty(value = "客户（bs_customer）")
    private Long customer;

    @ApiModelProperty(value = "状态 0已保存 1审核中 2已审核(已生效) 3已取消(已失效)")
    private Integer state;

    @ApiModelProperty(value = "失效时间")
    private Date invalidTime;


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

    public Long getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

}
