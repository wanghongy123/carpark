package com.github.wanghongy132.carpark.m.web.response;

import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 * @author wanruxiuer
 */
public class AnnotationPackagingActuator implements ResponsePackagingActuator {

    private final ResponseBuilder<?> responseBuilder;
    private final boolean enableAuto;
    private final String defaultSuccess;
    private final String defaultFailed;
    private final List<Class<?>> ignoreHandlerClasses;

    public AnnotationPackagingActuator(
            AnnotationResponsePackConfiguration configuration,
            ResponseBuilder<?> responseBuilder
    ) {
        AnnotationResponsePackConfiguration.Auto auto = configuration.getAuto();
        this.enableAuto = auto.isEnable();
        this.ignoreHandlerClasses = auto.getIgnoreHandlerClasses();
        this.defaultSuccess = auto.getDefaultSuccessMessage();
        this.defaultFailed = auto.getDefaultFailedMessage();
        this.responseBuilder = responseBuilder;
    }

    @Override
    public boolean supports(Method method, Class<?> handlerClass) {
        if (method.isAnnotationPresent(NotPack.class)) {
            return false;
        }
        if (enableAuto) {
            return !ignoreHandlerClasses.contains(handlerClass);
        }
        return method.isAnnotationPresent(BodyPackSetting.class)
                || method.isAnnotationPresent(ExecutionFailed.class)
                || method.isAnnotationPresent(ExecutionSuccess.class);
    }

    @Override
    public Object doSuccess(Object body, Method method, Class<?> handlerClass, ServerHttpRequest request, ServerHttpResponse response) {
        BodyPackSetting setting = method.getAnnotation(BodyPackSetting.class);
        ExecutionSuccess success = Optional.ofNullable(method.getAnnotation(ExecutionSuccess.class)).orElse(Optional.ofNullable(setting).map(BodyPackSetting::success).orElse(null));
        if (success != null) {
            response.getHeaders().setContentType(MediaType.parseMediaType(success.type()));
            if (success.packing()) {
                return responseBuilder.build(success.value(), body, new Object[0]);
            } else {
                return body;
            }
        }
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return responseBuilder.build(defaultSuccess, body, new Object[0]);
    }

    @Override
    public Failed.ApiException createFailed(Method method, Class<?> handlerClass, ServerHttpRequest request, ServerHttpResponse response, Throwable throwable, Object[] varargs) {
        BodyPackSetting setting = method.getAnnotation(BodyPackSetting.class);
        ExecutionFailed failed = Optional.ofNullable(method.getAnnotation(ExecutionFailed.class)).orElse(Optional.ofNullable(setting).map(BodyPackSetting::failed).orElse(null));
        if (failed != null) {
            response.getHeaders().setContentType(MediaType.parseMediaType(failed.type()));
            return new Failed.ApiException(throwable,failed.value(), varargs);
        }
        return new Failed.ApiException(throwable,defaultFailed, varargs);
    }
}
