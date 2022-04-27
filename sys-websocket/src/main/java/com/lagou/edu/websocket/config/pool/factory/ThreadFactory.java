package com.sys.test.websocket.config.pool.factory;

@FunctionalInterface
//创建线程的工厂
public interface ThreadFactory {

    Thread creatThread(Runnable runnable);
}
