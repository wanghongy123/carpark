package com.github.wanghongy132.carpark.m.adapter.api.entity;

import com.github.wanghongy132.carpark.m.domain.value.ResponseTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private Integer code;
    private Object data;
    private String msg;
    private ResponseTypeEnum type;
}
