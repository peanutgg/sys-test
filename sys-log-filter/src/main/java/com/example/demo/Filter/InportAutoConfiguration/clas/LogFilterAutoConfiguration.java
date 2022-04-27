package com.example.demo.Filter.InportAutoConfiguration.clas;

import com.example.demo.Filter.LogFilter;
import com.example.demo.Filter.registritrationBean.LogFilterRegistrationBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({LogFilterRegistrationBean.class, LogFilter.class})
public class LogFilterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(LogFilterRegistrationBean.class)
    public LogFilterRegistrationBean LogFilterAutoConfiguration() {

        return new LogFilterRegistrationBean();
    }
}
