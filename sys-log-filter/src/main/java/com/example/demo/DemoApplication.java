package com.example.demo;

import com.example.demo.Conf.Conf;
import com.example.demo.Filter.InportAutoConfiguration.annotations.EnableLogFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
@EnableLogFilter
public class DemoApplication {
    @Autowired
    private Conf conf;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/test")
    public String test() {

        log.info(conf.getName());
        log.info(conf.getName());

        return "hello world";
    }
}
