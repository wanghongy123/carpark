package com.github.wanghongy132.carpark.m.domain;

import java.util.List;
import java.util.stream.Collectors;

public interface MutualConverter<A,B> extends ObjectConverter{

    B convert(A a);
    A reverseConvert(B b);
    default List<B> convertBatch(List<A> aList){
        return aList.stream().map(this::convert).collect(Collectors.toList());
    }
    default List<A> reverseBatchConvert(List<B> bList){
        return bList.stream().map(this::reverseConvert).collect(Collectors.toList());
    }
}
