package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 任务配置
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@TableName("bs_task_config")
@ApiModel(value = "TaskConfig对象", description = "任务配置")
public class TaskConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "接口名称")
    private String dname;

    @ApiModelProperty(value = "类型（bs_dict：taskType）")
    private Integer type;

    @ApiModelProperty(value = "是否启用 0禁用  1启用")
    private Boolean isEnabled;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "备注")
    private String memo;

    private String param1;

    private String param2;

    private String param3;

    private String param4;

    private String param5;

    private String param6;


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

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public String getParam4() {
        return param4;
    }

    public void setParam4(String param4) {
        this.param4 = param4;
    }

    public String getParam5() {
        return param5;
    }

    public void setParam5(String param5) {
        this.param5 = param5;
    }

    public String getParam6() {
        return param6;
    }

    public void setParam6(String param6) {
        this.param6 = param6;
    }

    @Override
    public String toString() {
        return "TaskConfig{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", dname=" + dname +
                ", type=" + type +
                ", isEnabled=" + isEnabled +
                ", url=" + url +
                ", memo=" + memo +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", param4=" + param4 +
                ", param5=" + param5 +
                ", param6=" + param6 +
                "}";
    }
}
