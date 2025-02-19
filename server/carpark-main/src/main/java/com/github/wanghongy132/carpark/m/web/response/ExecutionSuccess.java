package com.github.wanghongy132.carpark.m.web.response;

import org.springframework.http.MediaType;
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
public @interface ExecutionSuccess {
    /**
     * 留空将自动寻找默认包装
     */
    String value() default "";

    String type() default MediaType.APPLICATION_JSON_VALUE;

    /**
     * 此项为 false 时将不包装
     */
    boolean packing() default true;
}
