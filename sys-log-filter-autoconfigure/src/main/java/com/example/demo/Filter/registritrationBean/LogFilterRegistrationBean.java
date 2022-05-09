package com.example.demo.Filter.registritrationBean;

import com.example.demo.Filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.stereotype.Component;

@Component
public class LogFilterRegistrationBean extends FilterRegistrationBean<LogFilter> {


    public LogFilterRegistrationBean() {

        super();

        this.setFilter(new LogFilter());
        this.addUrlPatterns("/*");
        this.setName("LogFilter");
        this.setOrder(1);

    }
}
