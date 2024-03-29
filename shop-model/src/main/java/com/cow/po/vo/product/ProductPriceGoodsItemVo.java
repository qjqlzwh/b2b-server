package com.cow.po.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 产品价格 - 商品明细
 * </p>
 *
 * @author cow
 * @since 2021-02-22
 */
@ApiModel(value = "ProductPriceGoodsItemVo对象", description = "产品价格 - 商品明细")
public class ProductPriceGoodsItemVo implements Serializable {

    private static final long serialVersionUID = 2315561609494023296L;

    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "产品价格主表（bs_product_price）")
    private Long productPrice;

    @ApiModelProperty(value = "行号")
    private Integer seq;

    @ApiModelProperty(value = "产品（bs_product）")
    private Long product;

    @ApiModelProperty(value = "状态 0已保存 1审核中 2已审核(已生效) 3已取消(已失效)")
    private Integer state;

    @ApiModelProperty(value = "失效时间")
    private Date invalidTime;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "使用数量")
    private BigDecimal useQuantity;


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

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUseQuantity() {
        return this.useQuantity;
    }

    public void setUseQuantity(BigDecimal useQuantity) {
        this.useQuantity = useQuantity;
    }
}
