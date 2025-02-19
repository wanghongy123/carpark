package com.github.wanghongy132.carpark.m.adapter.api;

import com.github.wanghongy132.carpark.m.adapter.api.entity.Response;
import com.github.wanghongy132.carpark.m.domain.service.ResponseMessageService;
import com.github.wanghongy132.carpark.m.domain.value.ResponseTypeEnum;
import com.github.wanghongy132.carpark.m.web.response.ResponseBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageDrivenResponseBuilder implements ResponseBuilder<Response> {

    private final ResponseMessageService responseMessageService;
    public MessageDrivenResponseBuilder(ResponseMessageService responseMessageService){
        this.responseMessageService = responseMessageService;
    }
    @Override
    public Response build(String msg, Object data, Object[] varargs) {
        Response response = new Response();
        response.setData(data);
        responseMessageService.findByMessageKey(msg).ifPresentOrElse(message -> {
            response.setMsg(message.getMessageTemplate().format(varargs));
            response.setCode(message.getId());
            response.setType(message.getType());
        }, ()->{
            response.setMsg(msg);
            response.setCode(-1);
            response.setType(ResponseTypeEnum.INFO);
        });
        return response;
    }
}
