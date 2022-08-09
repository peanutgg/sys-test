package com.sys.juc.test.future;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class Test {
    private InheritableThreadLocal<Integer> inheritableThreadLocalData = new InheritableThreadLocal<>();

    private ThreadLocal<Integer> threadLocalData = new ThreadLocal<>();

    @org.junit.Test
    public void testInheritableThreadLocal() throws InterruptedException, ExecutionException {
        inheritableThreadLocalData.set(1);
        threadLocalData.set(1);
        System.out.println("current thread get InheritableThreadLocal data:" + inheritableThreadLocalData.get() +
                " get ThreadLocal data:" + threadLocalData.get());
        new Thread(() -> System.out.println("son thread get InheritableThreadLocal data:" + inheritableThreadLocalData.get() +
                " get ThreadLocal data:" + threadLocalData.get())).start();
    }


    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    private ReentrantLock lock = new ReentrantLock();

    @org.junit.Test
    public void testAqs() {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
    }

    @org.junit.Test
    public void test(){
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop());
        }
    }
}
