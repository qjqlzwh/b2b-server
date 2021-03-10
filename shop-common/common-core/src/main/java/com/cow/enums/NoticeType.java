package com.cow.enums;

/**
 * 通知类型<br/>
 * bs_dict 系统词汇 noticeType
 */
public enum NoticeType {

    ORDER(0, "订单"),
    DELIVERY(1, "发货")
    ;

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
