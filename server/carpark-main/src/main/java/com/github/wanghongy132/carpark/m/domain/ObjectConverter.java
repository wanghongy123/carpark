package com.github.wanghongy132.carpark.m.domain;

public interface ObjectConverter {
    default <T> T getValue(ValueObject<T> valueObject) {
        return valueObject.getValue();
    }
}
