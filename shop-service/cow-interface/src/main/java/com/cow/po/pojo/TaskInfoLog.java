package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 任务日志历史表（报文）
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@TableName("bs_task_info_log")
@ApiModel(value = "TaskInfoLog对象", description = "任务日志历史表（报文）")
public class TaskInfoLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "请求报文")
    private String reqMsg;

    @ApiModelProperty(value = "类型（对应 bs_task_config 表的 type）")
    private Integer type;

    @ApiModelProperty(value = "唯一标识")
    private String sn;

    private String param1;

    private String param2;

    private String param3;

    private String param4;

    private String param5;

    private String param6;

    @ApiModelProperty(value = "响应时间")
    private Date respTime;

    @ApiModelProperty(value = "响应结果（0异常  1成功  2失败  3作废）")
    private Integer respResult;

    @ApiModelProperty(value = "响应报文")
    private String respMsg;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "报文来源（0客户端，1服务端）")
    private Integer docType;


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

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    public Date getRespTime() {
        return this.respTime;
    }

    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    public Integer getRespResult() {
        return respResult;
    }

    public void setRespResult(Integer respResult) {
        this.respResult = respResult;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }

}
