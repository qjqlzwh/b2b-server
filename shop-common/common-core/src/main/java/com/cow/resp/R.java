package com.cow.resp;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 统一响应
 * @param <T>
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    private R() {
    }

    public R(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public R(RespEnum respEnum) {
        this.code = respEnum.getCode();
        this.message = respEnum.getMessage();
    }

    public static R ok() {
        return new R(RespEnum.SUCCESS);
    }

    public static R ok(String message) {
        return new R(RespEnum.SUCCESS.getCode(), message);
    }

    public static R error() {
        return new R(RespEnum.ERROR);
    }

    public static R error(String message) {
        return new R(RespEnum.ERROR.getCode(), message);
    }

    public R data(T data) {
        this.data = data;
        return this;
    }

    public R msg(String message) {
        this.message = message;
        return this;
    }

    public R code(Integer code) {
        this.code = code;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
