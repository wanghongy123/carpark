package com.github.wanghongy132.carpark.m.web.response;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Optional;


/**
 * @author wanruxiuer
 */
@Component
@Aspect
public class UnifiedFailureResponse {

    private final ResponsePackagingActuatorManager responsePackagingActuatorManager;

    public UnifiedFailureResponse(
            ResponsePackagingActuatorManager responsePackagingActuatorManager
    ) {
        this.responsePackagingActuatorManager = responsePackagingActuatorManager;
    }

    @Around(value = """
            @within(org.springframework.web.bind.annotation.RestController)
            && (@annotation(org.springframework.web.bind.annotation.GetMapping)
            || @annotation(org.springframework.web.bind.annotation.PostMapping)
            || @annotation(org.springframework.web.bind.annotation.DeleteMapping)
            || @annotation(org.springframework.web.bind.annotation.PatchMapping)
            || @annotation(org.springframework.web.bind.annotation.RequestMapping)
            || @annotation(org.springframework.web.bind.annotation.PutMapping))
            """)
    public Object aroundRequestHandler(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        try {
            return point.proceed(point.getArgs());
        } catch (Failed.ApiException e) {
            throw e;
        } catch (Throwable e) {
            Class<?> handlerClass = method.getDeclaringClass();
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            throw responsePackagingActuatorManager.getActuator(method, handlerClass)
                    .createFailed(method, handlerClass, new ServletServerHttpRequest(requestAttributes.getRequest()), new ServletServerHttpResponse(Optional.ofNullable(requestAttributes.getResponse()).orElseThrow()), e, new Object[]{e.getMessage()});
        }
    }
}
