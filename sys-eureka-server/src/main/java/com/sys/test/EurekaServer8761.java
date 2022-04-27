package com.sys.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.io.IOException;

/**
 * @author admin
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer8761 {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EurekaServer8761.class, args);
    }
}
