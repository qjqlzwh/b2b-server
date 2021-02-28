package com.cow.feign.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class ProductVo {

    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "产品名称")
    private String dname;

    @ApiModelProperty(value = "产品编码")
    private String dcode;

    @ApiModelProperty(value = "产品类目（bs_category）")
    private Long category;

    @ApiModelProperty(value = "产品型号")
    private String model;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "出厂价")
    private BigDecimal price;

    @ApiModelProperty(value = "产品图片")
    private String image;

    @ApiModelProperty(value = "是否上架")
    private Boolean isShelves;


    public Long getId() {
        return this.id;
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

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDcode() {
        return this.dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public Long getCategory() {
        return this.category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIsShelves() {
        return this.isShelves;
    }

    public void setIsShelves(Boolean shelves) {
        this.isShelves = shelves;
    }
}
