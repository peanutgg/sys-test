package com.sys.test.websocket.config.pool.exception;

public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String msg){
        super(msg);
    }
}