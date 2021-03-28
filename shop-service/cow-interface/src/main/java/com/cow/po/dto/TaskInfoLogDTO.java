package com.cow.po.dto;

import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 任务日志历史表（报文）
 * </p>
 *
 * @author cow
 * @since 2021-03-17
 */
@ApiModel(value = "TaskInfoLog对象", description = "任务日志历史表（报文）")
public class TaskInfoLogDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "请求报文")
    private String reqMsg;

    @ApiModelProperty(value = "类型（对应 bs_task_config 表的 type）")
    private Integer type;

    @ApiModelProperty(value = "唯一标识")
    private String sn;

    @ApiModelProperty(value = "响应时间")
    private Date respDate;

    @ApiModelProperty(value = "响应结果（0异常  1成功  2失败  3作废）")
    private Integer respResult;

    @ApiModelProperty(value = "响应报文")
    private String respMsg;

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

    public Date getRespDate() {
        return respDate;
    }

    public void setRespDate(Date respDate) {
        this.respDate = respDate;
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

    public Integer getDocType() {
        return docType;
    }

    public void setDocType(Integer docType) {
        this.docType = docType;
    }

}
