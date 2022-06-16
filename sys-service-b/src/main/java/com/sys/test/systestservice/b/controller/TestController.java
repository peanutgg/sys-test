package com.sys.test.systestservice.b.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/rateTest")
    public String testRate(){
        return "ok";
    }
}
