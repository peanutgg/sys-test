package com.sys.test.websocket.config.pool.policy;

import com.sys.test.websocket.config.pool.ThreadPool;
import com.sys.test.websocket.config.pool.exception.RunnableDenyException;

@FunctionalInterface
//这个类定义了当缓存队列达到上限的时候，将通过什么方式来通知提交者，实现了默认的三种方法
public interface DenyPolicy {
    void reject(Runnable runnable, ThreadPool threadPool);


    //直接丢弃线程，什么都不做，不通知
    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }

    //抛出异常通知
    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException("这个线程:" + runnable + " 将会被丢弃");
        }
    }

    //使线程在提交者所在的线程中运行
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
