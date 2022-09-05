package com.sys.test.service.a.controller;

import com.sys.test.service.a.entity.UserInfo;
import com.sys.test.service.a.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AServiceController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/rateTest")
    public String testRate() {
        return "ok";
    }


    @RequestMapping("/user/add/{name}")
    public Integer addUser(@PathVariable("name") String name) {
        return userService.addUser(name);
    }

    @RequestMapping("/user/get/{id}")
    public List<UserInfo> addUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/a/testTimeOut/{id}")
    public String test_timeOut(@PathVariable("id") Integer id) {
        return userService.test_timeOut(id);
    }

    @GetMapping("/a/testTimeOut2/{id}")
    public String test_timeOut2(@PathVariable("id") Integer id) {
        return userService.test_timeOut2(id);
    }

    @GetMapping("/a/testTimeOut3/{id}")
    public String test_timeOut3(@PathVariable("id") Integer id) {
        return userService.test_timeOut3(id);
    }
}
