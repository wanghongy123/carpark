package com.github.wanghongy132.carpark.m.web.response;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.lang.reflect.Method;

/**
 * @author wanruxiuer
 */
public class DefaultResponsePackagingActuator implements ResponsePackagingActuator{

    public static final ResponsePackagingActuator DEFAULT_ACTUATOR = new DefaultResponsePackagingActuator();
    @Override
    public boolean supports(Method method, Class<?> handlerClass) {
        return true;
    }

    @Override
    public Object doSuccess(Object body, Method method, Class<?> handlerClass, ServerHttpRequest request, ServerHttpResponse response) {
        return body;
    }

    @Override
    public Failed.ApiException createFailed(Method method, Class<?> handlerClass, ServerHttpRequest request, ServerHttpResponse response, Throwable throwable, Object[] varargs) {
        return null;
    }


}
