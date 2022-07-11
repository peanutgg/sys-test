package com.sys.test.service.a;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.sys.test.service.a.mapper")
public class SysTestServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysTestServiceAApplication.class, args);
    }

}
