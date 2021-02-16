package com.cow.exception;

import com.cow.resp.RespEnum;

public class CowException extends RuntimeException {

    //状态码
    private Integer code;

    //异常信息
    private String msg;

    public CowException(RespEnum respEnum) {
        super(respEnum.getMessage());
        this.code = respEnum.getCode();
        this.msg = respEnum.getMessage();
    }

    public CowException(String msg) {
        super(msg);
        this.code = RespEnum.ERROR.getCode();
        this.msg = msg;
    }

    public CowException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
