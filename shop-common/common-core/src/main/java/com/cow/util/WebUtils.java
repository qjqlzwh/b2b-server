package com.cow.util;

import com.cow.jwt.JwtUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtils {

    /**
     * 获取 HttpServletRequest
     * @return {HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取 HttpServletResponse
     * @return {HttpServletResponse}
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取登录用户名
     * @return
     */
    public static String getLoginUsername() {
        return JwtUtils.getValByJwtToken(WebUtils.getRequest(), "username");
    }

    /**
     * 获取登录用户id
     * @return
     */
    public static String getLoginUserId() {
        return JwtUtils.getValByJwtToken(WebUtils.getRequest(), "id");
    }
}
