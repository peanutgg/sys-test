package com.sys.test.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sys.**.mapper")
public class SysAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysAuthApplication.class, args);
    }

}
