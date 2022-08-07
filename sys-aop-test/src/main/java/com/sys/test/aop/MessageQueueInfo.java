package com.sys.test.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component("messageQueueInfo")
@Slf4j
public class MessageQueueInfo {
    @MessageQueueRequire1
    public void testAop1() {
//        int a = 1 / 0;
        log.info("MessageQueueRequire1");
    }

    @MessageQueueRequire2
    public void testAop2() {
        log.info("MessageQueueRequire2");
    }
}
