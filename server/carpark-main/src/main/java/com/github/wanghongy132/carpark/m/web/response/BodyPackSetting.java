package com.github.wanghongy132.carpark.m.web.response;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wanruxiuer
 */
@ResponseBody
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BodyPackSetting {
    /**
     * 在 handler 执行成功时将该项对应的消息包入响应
     */
    ExecutionSuccess success() default @ExecutionSuccess;

    /**
     * 在 handler 抛出异常时将该项对应的消息包入响应
     */
    ExecutionFailed failed() default @ExecutionFailed;
}
