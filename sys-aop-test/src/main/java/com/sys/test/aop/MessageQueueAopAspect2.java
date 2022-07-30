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
public class MessageQueueAopAspect2 implements Ordered {

    @Pointcut("@annotation(com.sys.test.aop.MessageQueueRequire2)")
    private void pointCutMethod() {
    }


    //声明前置通知
    @Before("pointCutMethod()")
    public void doBefore(JoinPoint point) {
        log.info("MessageQueueAopAspect2:doBefore");
        return;
    }

    //声明后置通知
    @AfterReturning(pointcut = "pointCutMethod()", returning = "returnValue")
    public void doAfterReturning(JoinPoint point,Object returnValue) {
        log.info("MessageQueueAopAspect2:doAfterReturning");
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        log.info("MessageQueueAopAspect2:doAfterThrowing");
    }

    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter() {
        log.info("MessageQueueAopAspect2:doAfter");
    }

    //声明环绕通知
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("MessageQueueAopAspect2:doAround-1");
        Object obj = pjp.proceed();
        log.info("MessageQueueAopAspect2:doAround-2");
        return obj;
    }

    @Override
    public int getOrder() {
        return 1002;
    }
}
