package com.sys.classloder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class);
        MyClassLoader myClassLoader = new MyClassLoader("D:\\IdeaProjects\\sys-test\\sys-classloader\\target\\classes\\com\\sys\\classloder\\");
        try {
            Class clazz = myClassLoader.findClass("HelloWorld.class");
            System.out.println(clazz);
            System.out.println(clazz.getConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
