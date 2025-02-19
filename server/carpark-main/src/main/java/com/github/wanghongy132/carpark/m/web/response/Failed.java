package com.github.wanghongy132.carpark.m.web.response;

import lombok.Getter;

/**
 * @author wanruxiuer
 */
public final class Failed {

    public static <T> T msg(String msgId, Object... varargs) {
        throw new ApiException(msgId, varargs);
    }

    @Getter
    public static class ApiException extends RuntimeException {
        private final String msgId;
        private final Object[] varargs;


        public ApiException(String msgId, Object... varargs) {
            super(msgId);
            this.msgId = msgId;
            this.varargs = varargs;
        }

        public ApiException(Throwable throwable, String msgId, Object... varargs) {
            super(msgId, throwable);
            this.msgId = msgId;
            this.varargs = varargs;
        }

    }
}
