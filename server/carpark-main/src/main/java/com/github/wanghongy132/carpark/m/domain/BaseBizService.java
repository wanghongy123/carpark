package com.github.wanghongy132.carpark.m.domain;

import java.io.Serializable;
import java.util.List;

public interface BaseBizService<DM extends DomainModel, ID extends Serializable> {
    DM save(DM dm);

    DM findById(ID id);

    void deleteById(ID id);

    DM updateById(DM dm);

    List<DM> findList(DM dm);
}
