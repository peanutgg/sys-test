package com.sys.test.aop;

import com.example.demo.Filter.InportAutoConfiguration.annotations.EnableLogFilter;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableLogFilter
public class AopTestApplication implements CommandLineRunner, ApplicationContextAware {
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(AopTestApplication.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        MessageQueueInfo messageQueueInfo = (MessageQueueInfo) applicationContext.getBean("messageQueueInfo");
        messageQueueInfo.testAop2();
        System.out.println("----------------");
        messageQueueInfo.testAop1();
    }

    @RequestMapping("/rateTest")
    public String testRate(){
        return "ok";
    }
}
