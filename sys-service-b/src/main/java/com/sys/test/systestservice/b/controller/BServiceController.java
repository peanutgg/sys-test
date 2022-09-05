package com.sys.test.systestservice.b.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BServiceController {
    @RequestMapping("/rateTest")
    public String testRate() {
        return "ok";
    }

    @GetMapping("/b/testTimeOut/{id}")
    public String test_timeOut(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("b service error");
        }
        return "ok";
    }

    @GetMapping("/b/testTimeOut2/{id}")
    public String test_timeOut2(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("b service error");
        }
        return "ok";
    }

    @GetMapping("/b/testTimeOut3/{id}")
    public String test_timeOut3(@PathVariable("id") Integer id) {
        if (id % 2 == 0) {
            throw new RuntimeException("b service error");
        }
        return "ok";
    }
}
