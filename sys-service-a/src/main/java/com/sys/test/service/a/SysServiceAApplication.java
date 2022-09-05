package com.sys.test.service.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.sys.test.service.a.mapper")
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = {"com.sys.test.**.api", "com.sys.test.**.feignclient"})
public class SysServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysServiceAApplication.class, args);
    }

}
