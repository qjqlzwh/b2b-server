package com.cow.exception;

import com.cow.resp.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获 Exception 异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error();
    }

    /**
     * 捕获 IllegalArgumentException
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public R error(IllegalArgumentException e) {
        e.printStackTrace();
        return R.error().msg(e.getMessage());
    }

    /**
     * 捕获 CowException
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(CowException.class)
    public R error(CowException e) {
        e.printStackTrace();
        return R.error().msg(e.getMessage());
    }

}
