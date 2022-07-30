package com.sys.test.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Aspect
@Slf4j
public class MessageQueueAopAspect1 implements Ordered {


    @Pointcut("@annotation(com.sys.test.aop.MessageQueueRequire1)")
    private void pointCutMethod() {
    }

    //声明前置通知
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint point) {
        log.info("MessageQueueAopAspect1:doBefore");
        return;
    }

    //声明后置通知
    @AfterReturning(pointcut = "pointCutMethod()", returning = "returnValue")
    public void doAfterReturning(JoinPoint point,Object returnValue) {
        log.info("MessageQueueAopAspect1:doAfterReturning");
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        log.info("MessageQueueAopAspect1:doAfterThrowing");
    }

    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter() {
        log.info("MessageQueueAopAspect1:doAfter");
    }

    //声明环绕通知
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("MessageQueueAopAspect1:doAround-1");
        Object obj = pjp.proceed();
        log.info("MessageQueueAopAspect1:doAround-2");
        return obj;
    }

    @Override
    public int getOrder() {
        return 1001;
    }
}
