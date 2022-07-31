package com.sys.test.designmode;

import com.sys.test.designmode.eventlistener.UserService;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: 橘子
 * @date: 2022/7/21 14:27
 */
@SpringBootApplication
public class DesignModeTestMain implements CommandLineRunner, ApplicationContextAware {
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DesignModeTestMain.class);
    }

    @Override
    public void run(String... args) throws Exception {
        UserService userService= (UserService) applicationContext.getBean("userService");
        userService.register("xxd");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}








