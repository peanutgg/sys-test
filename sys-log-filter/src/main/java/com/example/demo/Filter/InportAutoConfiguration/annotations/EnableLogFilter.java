package com.example.demo.Filter.InportAutoConfiguration.annotations;

import com.example.demo.Filter.InportAutoConfiguration.clas.LogFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LogFilterAutoConfiguration.class)
public @interface EnableLogFilter {

}
