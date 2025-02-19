package com.github.wanghongy132.carpark.m.infrastructure.persistence.converter;

import com.github.wanghongy132.carpark.m.domain.model.ResponseMessage;
import com.github.wanghongy132.carpark.m.domain.value.MessageTemplateValue;
import com.github.wanghongy132.carpark.m.infrastructure.DomainModelAndDataEntityConverter;
import com.github.wanghongy132.carpark.m.infrastructure.persistence.entity.ResponseMessageEntity;
import org.mapstruct.Mapper;

@Mapper(uses = MessageTemplateValue.class, componentModel = "spring")
public interface ResponseMessageDomainModelAndDataEntityConverter extends DomainModelAndDataEntityConverter<ResponseMessage, ResponseMessageEntity> {
}
