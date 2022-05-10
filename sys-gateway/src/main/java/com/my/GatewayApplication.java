package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1、客户端发出请求给网关获取令牌
 * 2、网关收到请求，直接转发给授权服务
 * 3、授权服务验证用户名、密码等一系列身份，通过则颁发令牌给客户端
 * 4、客户端携带令牌请求资源，请求直接到了网关层
 * 5、网关对令牌进行校验（验签、过期时间校验....）、
 * 鉴权（对当前令牌携带的权限）和访问资源所需的权限进行比对，如果权限有交集则通过校验，直接转发给微服务
 */
@SpringBootApplication
@RestController
@EnableConfigurationProperties({GatewayApplication.UriConfiguration.class})
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p
                        .host("*.circuitbreaker.com")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("mycmd")
                                .setFallbackUri("forward:/fallback")))
                        .uri("http://httpbin.org:80")).
                build();
    }

    @RequestMapping("/fallback2")
    public String fallback() {
        return "fallback";
    }
    @ConfigurationProperties(prefix = "fallback")
    static class UriConfiguration {
        String uri;
    }
}
