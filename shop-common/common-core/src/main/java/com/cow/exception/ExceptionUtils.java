package com.cow.exception;

import com.cow.resp.RespEnum;

public class ExceptionUtils {

    public static void throwRowException(String msg) {
        throw new CowException(msg);
    }

    public static void throwRowException(Integer code, String msg) {
        throw new CowException(code, msg);
    }

    public static void throwRowException(RespEnum respEnum) {
        throw new CowException(respEnum);
    }

}
