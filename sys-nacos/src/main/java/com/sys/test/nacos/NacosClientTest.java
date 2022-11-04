package com.sys.test.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author admin
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosClientTest {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(NacosClientTest.class, args);
    }
}
