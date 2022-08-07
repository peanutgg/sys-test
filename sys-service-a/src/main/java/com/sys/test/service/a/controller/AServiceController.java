package com.sys.test.service.a.controller;

import com.sys.test.service.a.entity.UserInfo;
import com.sys.test.service.a.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AServiceController {
    @RequestMapping("/rateTest")
    public String testRate() {
        return "ok";
    }
    @Autowired
    UserService userService;
    @RequestMapping("/user/add/{name}")
    public Integer addUser(@PathVariable("name") String name) {
        return userService.addUser(name);
    }
    @RequestMapping("/user/get/{id}")
    public List<UserInfo> addUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }
}
