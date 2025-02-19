package com.github.wanghongy132.carpark.m.domain.value;

import com.github.wanghongy132.carpark.m.domain.SingleValueObject;

import java.text.MessageFormat;

/**
 * @author wanruxiuer
 */
public class MessageTemplateValue extends SingleValueObject<String> {

    public MessageTemplateValue(String value) {
        super(value);
    }

    public String format(Object... varargs){
        return MessageFormat.format(this.getValue(), varargs);
    }

    public static MessageTemplateValue valueOf(String value){
        return new MessageTemplateValue(value);
    }

}
