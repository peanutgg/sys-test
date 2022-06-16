package com.sys.test.systestservice.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SysTestServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysTestServiceBApplication.class, args);
    }

}
