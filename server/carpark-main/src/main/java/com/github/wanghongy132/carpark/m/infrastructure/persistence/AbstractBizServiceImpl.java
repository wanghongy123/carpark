package com.github.wanghongy132.carpark.m.infrastructure.persistence;

import com.github.wanghongy132.carpark.m.domain.BaseBizService;
import com.github.wanghongy132.carpark.m.domain.DataEntity;
import com.github.wanghongy132.carpark.m.domain.DomainModel;
import com.github.wanghongy132.carpark.m.infrastructure.DomainModelAndDataEntityConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractBizServiceImpl<DM extends DomainModel, DE extends DataEntity, ID extends Serializable,
        Repository extends JpaRepository<DE, ID>
        > implements BaseBizService<DM, ID> {
    protected final Repository repository;
    protected final DomainModelAndDataEntityConverter<DM, DE> converter;

    public AbstractBizServiceImpl(Repository repository, DomainModelAndDataEntityConverter<DM, DE> converter) {
        this.repository = repository;
        this.converter = converter;
    }
    @Override
    public DM save(DM dm) {
        return converter.toDomainModel(repository.save(converter.toDataEntity(dm)));
    }

    @Override
    public DM findById(ID id) {
        return converter.toDomainModel(repository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public DM updateById(DM dm) {
        return converter.toDomainModel(repository.save(converter.toDataEntity(dm)));
    }

    @Override
    public List<DM> findList(DM dm) {
        return converter.reverseBatchConvert(repository.findAll());
    }
}
