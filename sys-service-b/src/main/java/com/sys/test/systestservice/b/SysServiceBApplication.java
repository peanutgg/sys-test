package com.sys.test.systestservice.b;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.sys.test.service.b.mapper")
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients
public class SysServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysServiceBApplication.class, args);
    }

}
