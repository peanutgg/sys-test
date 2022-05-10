//package com.my;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import javax.sql.DataSource;
//
///**
// * @author xxd
// * @Date 2022-2-28 10:47:00
// */
//@Configuration
//public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) throws Exception {
//        builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
//                .password("secret").roles("USER");
//    }
//}
