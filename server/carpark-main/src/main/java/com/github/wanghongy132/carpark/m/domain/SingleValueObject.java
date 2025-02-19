package com.github.wanghongy132.carpark.m.domain;


import java.util.Objects;

/**
 * @author wanruxiuer
 */

public abstract class SingleValueObject<T> implements ValueObject<T> {
    private final T value;
    public SingleValueObject(T value){
        this.value = value;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleValueObject<?> that = (SingleValueObject<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
