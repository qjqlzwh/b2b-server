package com.cow.po.enums;

/**
 * 通知类型
 */
public enum NoticeType {

    ORDER(0, "订单");

    private Integer val;

    private String memo;

    NoticeType(Integer val, String memo) {
        this.val = val;
        this.memo = memo;
    }

    public Integer getVal() {
        return this.val;
    }

    public String getMemo() {
        return this.memo;
    }
}
