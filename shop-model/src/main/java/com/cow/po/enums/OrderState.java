package com.cow.po.enums;

/**
 * 订单状态
 */
public enum OrderState {

    SAVE(0, "已保存"),
    SUBMIT(1, "已提交"),
    AUDIT(2, "已审核(已生效)"),
    CANCEL(3, "已取消(已失效)")
    ;

    private Integer state;

    private String memo;

    OrderState(Integer state, String memo) {
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
