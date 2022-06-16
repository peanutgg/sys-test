package com.sys.test.service.a.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.sys.test.service.a.entity.UserInfo;
import com.sys.test.service.a.hystrix.request.merge.UserCollapseCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
@RestController
public class HystrixTestController {

    /**
     * 模拟合并请求测试(非注解)
     * 这里通过
     */
    @GetMapping("/collapse")
    public void collapseTest() {
        System.out.println("==========>collapseTest方法执行了");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            Future<UserInfo> f1 = new UserCollapseCommand(1).queue();
            Future<UserInfo> f2 = new UserCollapseCommand(2).queue();
            Future<UserInfo> f3 = new UserCollapseCommand(3).queue();

            Thread.sleep(3000);

            Future<UserInfo> f4 = new UserCollapseCommand(5).queue();
            Future<UserInfo> f5 = new UserCollapseCommand(6).queue();

            UserInfo u1 = f1.get();
            UserInfo u2 = f2.get();
            UserInfo u3 = f3.get();

            UserInfo u4 = f4.get();
            UserInfo u5 = f5.get();
            System.out.println(u1.getName());
            System.out.println(u2.getName());
            System.out.println(u3.getName());
            System.out.println(u4.getName());
            System.out.println(u5.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.close();
        }

    }
    @RequestMapping("/rateTest")
    public String testRate(){
        return "ok";
    }
}
