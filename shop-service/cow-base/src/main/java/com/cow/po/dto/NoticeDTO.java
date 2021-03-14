package com.cow.po.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.cow.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 通知
 * </p>
 *
 * @author cow
 * @since 2021-03-09
 */
@ApiModel(value = "NoticeDTO对象", description = "通知")
public class NoticeDTO extends PageDTO {

    private Long id;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "通知类型（bs_dict：noticeType）")
    private Integer type;

    @ApiModelProperty(value = "主题")
    private String subject;


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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
