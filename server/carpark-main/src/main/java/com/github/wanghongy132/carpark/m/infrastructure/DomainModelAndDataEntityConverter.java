package com.github.wanghongy132.carpark.m.infrastructure;

import com.github.wanghongy132.carpark.m.domain.DataEntity;
import com.github.wanghongy132.carpark.m.domain.DomainModel;
import com.github.wanghongy132.carpark.m.domain.MutualConverter;

public interface DomainModelAndDataEntityConverter <DM extends DomainModel, DE extends DataEntity> extends MutualConverter<DM, DE> {
    DM toDomainModel(DE entity);

    DE toDataEntity(DM domainModel);

    @Override
    default DE convert(DM dm){
        return toDataEntity(dm);
    }

    @Override
    default DM reverseConvert(DE de){
        return toDomainModel(de);
    }
}
