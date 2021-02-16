package com.cow.filter;

//import com.cow.jwt.JwtUtils;
//import com.google.gson.JsonObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * 全局Filter，统一处理登录与外部不允许访问的服务
 * </p>
 */
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
//        String path = request.getURI().getPath();
//        // api接口，校验用户必须登录
//        if (antPathMatcher.match("/api/**/auth/**", path)) {
//            List<String> tokenList = request.getHeaders().get("token");
//            if (null == tokenList) {
//                ServerHttpResponse response = exchange.getResponse();
//                return out(response);
//            } else {
//                Boolean isCheck = JwtUtils.checkToken(tokenList.get(0));
//                if (!isCheck) {
//                    ServerHttpResponse response = exchange.getResponse();
//                    return out(response);
//                }
//            }
//        }
//        // 内部服务接口，不允许外部访问
//        if (antPathMatcher.match("/**/inner/**", path)) {
//            ServerHttpResponse response = exchange.getResponse();
//            return out(response);
//        }

        // 所有地址登录才能访问
        ServerHttpResponse response = exchange.getResponse();
//        List<String> tokenList = request.getHeaders().get("token");
//
//        if (CollectionUtils.isEmpty(tokenList)) {
//            return out(response);
//        }
//        // 校验token是否有效
//        Boolean isCheck = JwtUtils.checkToken(tokenList.get(0));
//        if (!isCheck) {
//            return out(response);
//        }

        return chain.filter(exchange);
    }

    /**
     * 数值越小,执行优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

   /* private Mono<Void> out(ServerHttpResponse response) {
        JsonObject message = new JsonObject();
//        message.addProperty("success", false);
        message.addProperty("code", 400);
        message.addProperty("message", "鉴权失败");
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        // response.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }*/
}
