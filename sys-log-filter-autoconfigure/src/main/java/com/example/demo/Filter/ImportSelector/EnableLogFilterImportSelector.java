package com.example.demo.Filter.ImportSelector;

import com.example.demo.Filter.InportAutoConfiguration.annotations.EnableLogFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * .
 * 如果需要在所有的@Configuration处理完再导入时可以实现DeferredImportSelector接口
 * BeanClassLoaderAware接口和BeanFactoryAware接口同理，可以分别获取Bean的类装载器和bean工厂
 */
@Slf4j
public class EnableLogFilterImportSelector implements DeferredImportSelector, BeanClassLoaderAware, EnvironmentAware {
    private final Class annotationClass = EnableLogFilter.class;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public String[] selectImports(AnnotationMetadata metadata) {
        //获取注解中的属性
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(this.annotationClass.getName()));
        Assert.notNull(annotationAttributes, "annotationAttributes can not be null...");
        ArrayList<String> factories = new ArrayList<>(new LinkedHashSet<>(SpringFactoriesLoader.loadFactoryNames(this.annotationClass, this.getClass().getClassLoader())));

        return factories.toArray(new String[factories.size()]);
    }

}
