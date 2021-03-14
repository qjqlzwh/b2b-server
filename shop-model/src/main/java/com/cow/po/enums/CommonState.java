package com.cow.po.enums;

/**
 * 通用状态
 */
public enum CommonState {

    SAVE(0, "已保存"),
    UNDER_REVIEW(1, "审核中"),
    AUDIT(2, "已审核(已生效)"),
    CANCEL(3, "已取消(已失效)")
    ;

    private Integer state;

    private String memo;

    CommonState(Integer state, String memo) {
        this.state = state;
        this.memo = memo;
    }

    public Integer getState() {
        return this.state;
    }

    public String getMemo() {
        return this.memo;
    }
}
