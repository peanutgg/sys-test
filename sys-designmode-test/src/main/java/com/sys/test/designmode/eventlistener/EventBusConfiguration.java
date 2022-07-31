package com.sys.test.designmode.eventlistener;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * AsyncEventBus 线程池配置
 *
 * @author wangmeng
 * @date 2020/04/14
 */
@Configuration
public class EventBusConfiguration {

    /** Set the ThreadPoolExecutor's core pool size. */
    private int corePoolSize = 10;
    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 30;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = 500;

    @Bean
    public AsyncEventBus asyncEventBus() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("jv-mall-user-sensorsData:");
        executor.initialize();
        return new AsyncEventBus(executor);
    }
}