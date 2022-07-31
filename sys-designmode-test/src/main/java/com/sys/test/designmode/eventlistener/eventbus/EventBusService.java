package com.sys.test.designmode.eventlistener.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 事件总线服务
 *
 * @author wangmeng
 * @date 2020/4/14
 */
@Service
public class EventBusService {
    /**
     * 订阅者异步执行器，如果同步可以使用EventBus
     **/
    @Autowired
    private AsyncEventBus asyncEventBus;
    /**
     * 订阅者集合，里面方法通过@Subscribe进行事件订阅
     **/
    @Autowired
    private EventListener eventListener;

    /**
     * 注册方法，启动的时候将所有的订阅者进行注册
     **/
    @PostConstruct
    public void register() {
        asyncEventBus.register(eventListener);
    }

    /**
     * 消息投递，根据入参自动投递到对应的方法中去消费。
     */
    public void post(Object object) {
        asyncEventBus.post(object);
    }
}
