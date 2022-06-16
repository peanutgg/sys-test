package com.sys.test.sysservice.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SysServiceCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysServiceCApplication.class, args);
    }

}
