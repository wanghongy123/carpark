package com.github.wanghongy132.carpark.m.domain.model;

import com.github.wanghongy132.carpark.m.domain.DomainModel;
import com.github.wanghongy132.carpark.m.domain.value.MessageTemplateValue;
import com.github.wanghongy132.carpark.m.domain.value.ResponseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wanruxiuer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage implements DomainModel {
    private Integer id;
    private MessageTemplateValue messageTemplate;
    private String messageKey;
    private ResponseTypeEnum type;
    private Integer responseStatus;
}
