package com.cow.resp;

public enum RespEnum {

    SUCCESS(200, "操作成功"),
    ERROR(400, "操作失败")

    ;

    private Integer code;

    private String message;

    RespEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
