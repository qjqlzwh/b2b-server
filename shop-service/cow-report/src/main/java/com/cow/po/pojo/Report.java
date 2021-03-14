package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

/**
 * <p>
 *  报表
 * </p>
 *
 * @author cow
 * @since 2021-03-06
 */
@TableName("bs_report")
@ApiModel(value = "Report对象", description = "")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
