package com.example.demo.Conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "xxd")
@Component
public class Conf {

    private String name;
}
