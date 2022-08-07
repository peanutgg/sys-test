package com.sys.test.service.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sys.test.service.a.mapper")
public class SysServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysServiceAApplication.class, args);
    }

}
