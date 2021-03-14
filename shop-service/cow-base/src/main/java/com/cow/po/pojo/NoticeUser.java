package com.cow.po.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 通知用户
 * </p>
 *
 * @author cow
 * @since 2021-03-09
 */
@TableName("bs_notice_user")
@ApiModel(value = "NoticeUser对象", description = "通知用户")
public class NoticeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "通知（bs_notice）")
    private Long notice;

    @ApiModelProperty(value = "接收人（bs_user）")
    private Long recipient;

    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;


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

    public Long getNotice() {
        return this.notice;
    }

    public void setNotice(Long notice) {
        this.notice = notice;
    }

    public Long getRecipient() {
        return this.recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public Boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(Boolean read) {
        this.isRead = read;
    }
}
