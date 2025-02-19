package com.github.wanghongy132.carpark.m.web.response;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author wanruxiuer
 */
public class ErrorPackagingActuator implements ResponsePackagingActuator {

    private final ResponseBuilder<?> responseBuilder;

    public ErrorPackagingActuator(ResponseBuilder<?> responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    @Override
    public boolean supports(Method method, Class<?> handlerClass) {
        return ErrorController.class.isAssignableFrom(handlerClass);
    }

    @Override
    public Object doSuccess(Object body, Method method, Class<?> handlerClass, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Map<?, ?> map) {
            Object status = map.get("status");
            if (status != null) {
                return responseBuilder.build(status.toString(), null, new Object[]{map.get("error")});
            } else {
                return body;
            }
        } else {
            return body;
        }
//        return body;
    }

    @Override
    public Failed.ApiException createFailed(Method method, Class<?> handlerClass, ServerHttpRequest request, ServerHttpResponse response, Throwable throwable, Object[] varargs) {
        return null;
    }
}
