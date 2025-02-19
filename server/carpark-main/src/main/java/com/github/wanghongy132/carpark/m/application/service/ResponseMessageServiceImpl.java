package com.github.wanghongy132.carpark.m.application.service;

import com.github.wanghongy132.carpark.m.domain.model.ResponseMessage;
import com.github.wanghongy132.carpark.m.domain.service.ResponseMessageService;
import com.github.wanghongy132.carpark.m.infrastructure.DomainModelAndDataEntityConverter;
import com.github.wanghongy132.carpark.m.infrastructure.persistence.AbstractBizServiceImpl;
import com.github.wanghongy132.carpark.m.infrastructure.persistence.ResponseMessageRepository;
import com.github.wanghongy132.carpark.m.infrastructure.persistence.converter.ResponseMessageDomainModelAndDataEntityConverter;
import com.github.wanghongy132.carpark.m.infrastructure.persistence.entity.ResponseMessageEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseMessageServiceImpl extends AbstractBizServiceImpl<ResponseMessage, ResponseMessageEntity, Integer, ResponseMessageRepository> implements ResponseMessageService {

    public ResponseMessageServiceImpl(
            ResponseMessageRepository repository,
            DomainModelAndDataEntityConverter<ResponseMessage, ResponseMessageEntity> converter) {
        super(repository, converter);
    }

    @Override
    public Optional<ResponseMessage> findByMessageKey(String messageKey) {
        return repository.findByMessageKey(messageKey).map(converter::toDomainModel);
    }
}
