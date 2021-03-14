package com.cow.po.vo.order;


import java.io.Serializable;

/**
 * 订单
 */
public class OrderVo implements Serializable {

    private static final long serialVersionUID = 8930127450627675800L;

    // 订单id
    private Long id;

    // 订单号
    private String sn;

    // 客户（bs_customer）
    private Long customer;

    // 业务员（bs_user）
    private Long salesman;

    // 消息内容
    private String content;

    // 消息主题
    private String subject;

    // 消息创建人
    private String creator;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSn() {
        return this.sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getCustomer() {
        return this.customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Long getSalesman() {
        return this.salesman;
    }

    public void setSalesman(Long salesman) {
        this.salesman = salesman;
    }
}
