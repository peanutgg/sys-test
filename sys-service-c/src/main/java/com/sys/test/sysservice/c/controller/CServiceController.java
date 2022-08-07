package com.sys.test.sysservice.c.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CServiceController {
    @RequestMapping("/rateTest")
    public String testRate() {
        return "ok";
    }
}
