package com.sys.test.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

@SpringBootApplication
public class AopTestApplication implements CommandLineRunner, ApplicationContextAware {
    ApplicationContext applicationContext;
    public static void main(String[] args)  {
        SpringApplication.run(AopTestApplication.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        MessageQueueInfo messageQueueInfo = (MessageQueueInfo) applicationContext.getBean("messageQueueInfo");
        messageQueueInfo.testAop1();
        System.out.println("----------------");
        messageQueueInfo.testAop2();
    }
}
