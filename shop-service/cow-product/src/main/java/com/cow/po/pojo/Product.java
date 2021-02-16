package com.cow.po.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *  产品
 * </p>
 *
 * @author cow
 * @since 2021-02-12
 */
@TableName("bs_product")
@ApiModel(value = "Product对象", description = "产品")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

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
        return id;
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

    public Date getUpdateTime() {
        return this.updateTime;
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
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
