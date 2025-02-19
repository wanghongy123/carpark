package com.github.wanghongy132.carpark.m.domain.service;


import com.github.wanghongy132.carpark.m.domain.BaseBizService;
import com.github.wanghongy132.carpark.m.domain.model.ResponseMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("responseMessageService")
public interface ResponseMessageService extends BaseBizService<ResponseMessage, Integer> {
    Optional<ResponseMessage> findByMessageKey(String messageKey);
}
