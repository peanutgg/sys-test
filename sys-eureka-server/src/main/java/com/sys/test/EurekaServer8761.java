package com.sys.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.io.IOException;

/**
 * @author admin
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaServer
@EnableDiscoveryClient
public class EurekaServer8761 {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EurekaServer8761.class, args);
    }
}
