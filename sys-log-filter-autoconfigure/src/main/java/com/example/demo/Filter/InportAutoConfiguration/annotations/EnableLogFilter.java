package com.example.demo.Filter.InportAutoConfiguration.annotations;

import com.example.demo.Filter.ImportSelector.EnableLogFilterImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 橘子
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableLogFilterImportSelector.class)
public @interface EnableLogFilter {

}
