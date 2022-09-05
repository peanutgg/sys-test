package com.sys.test.systestservice.b.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-b")
public interface BServiceApi {
    @GetMapping("/b/testTimeOut/{id}")
    String test_timeOut(@PathVariable("id") Integer id);

    @GetMapping("/b/testTimeOut2/{id}")
    String test_timeOut2(@PathVariable("id") Integer id);

    @GetMapping("/b/testTimeOut3/{id}")
    public String test_timeOut3(@PathVariable("id") Integer id);
}


