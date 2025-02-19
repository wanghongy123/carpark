package com.github.wanghongy132.carpark.m.web.response;

import java.io.Serializable;

/**
 * 响应构造器
 * 须实现将数据与消息包装为响应对象的功能
 *
 * @param <T> 响应对象类型
 * @author wanruxiuer
 */
public interface ResponseBuilder<T extends Serializable> {
    T build(
            String msg,
            Object data,
            Object[] varargs
    );
}
