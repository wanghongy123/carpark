package com.github.wanghongy132.carpark.m.web.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJsonHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
/**
 * @author wanruxiuer
 */
@ControllerAdvice
public class JsonResponseBodyPacker implements ResponseBodyAdvice<Object> {

    private final ResponsePackagingActuatorManager responsePackagingActuatorManager;
    private final ResponseBuilder<?> responseBuilder;
    private final ObjectMapper objectMapper;


    public JsonResponseBodyPacker(
            ResponseBuilder<?> responseBuilder,
            ResponsePackagingActuatorManager responsePackagingActuatorManager,
            ObjectMapper objectMapper
    ) {
        this.responseBuilder = responseBuilder;
        this.responsePackagingActuatorManager = responsePackagingActuatorManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(@NonNull MethodParameter parameter, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return !JsonResponseBodyPacker.class.isAssignableFrom(parameter.getDeclaringClass()) && (AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType)
                || AbstractJsonHttpMessageConverter.class.isAssignableFrom(converterType)
                || StringHttpMessageConverter.class.isAssignableFrom(converterType));
    }


    @Override
    public Object beforeBodyWrite(
            @Nullable Object body,
            @NonNull MethodParameter parameter,
            @NonNull MediaType selectedContentType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType,
            @NonNull ServerHttpRequest request,
            @NonNull ServerHttpResponse response
    ) {
        Method method = parameter.getMethod();
        if (method == null) {
            return body;
        }
        Class<?> handlerClass = method.getDeclaringClass();
        Object res = responsePackagingActuatorManager.getActuator(method,handlerClass).doSuccess(body, method, handlerClass, request, response);
        if (method.getReturnType().equals(String.class)) {
            return handleString(res);
        }
        return res;
    }

    public Object handleString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            return o;
        }
    }

    @ResponseBody
    @ExceptionHandler(Failed.ApiException.class)
    public Object handleApiException(Failed.ApiException e) {
        return responseBuilder.build(e.getMsgId(), null, e.getVarargs());
    }


}
