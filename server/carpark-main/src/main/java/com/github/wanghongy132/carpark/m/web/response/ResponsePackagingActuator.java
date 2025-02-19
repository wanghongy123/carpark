package com.github.wanghongy132.carpark.m.web.response;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.lang.reflect.Method;

/**
 * @author wanruxiuer
 */
public interface ResponsePackagingActuator {
    boolean supports(Method method, Class<?> handlerClass);

    Object doSuccess(
            Object body,
            Method method,
            Class<?> handlerClass,
            ServerHttpRequest request,
            ServerHttpResponse response
    );

    Failed.ApiException createFailed(Method method, Class<?> handlerClass, ServerHttpRequest request, ServerHttpResponse response, Throwable throwable, Object[] varargs);
}
