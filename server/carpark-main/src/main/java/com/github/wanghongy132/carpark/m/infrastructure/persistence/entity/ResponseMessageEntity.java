package com.github.wanghongy132.carpark.m.infrastructure.persistence.entity;

import com.github.wanghongy132.carpark.m.domain.DataEntity;
import com.github.wanghongy132.carpark.m.domain.value.ResponseTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author wanruxiuer
 */
@Entity
@Table(name = "response_message")
@Data
public class ResponseMessageEntity implements DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String messageTemplate;
    private String messageKey;
    @Enumerated(EnumType.STRING)
    private ResponseTypeEnum type;
    private Integer responseStatus;
}
