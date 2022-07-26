package com.sys.executor;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

public class AAA {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        AtomicInteger a1 = new AtomicInteger(1);
        a1.set(1);
        a1.get();


        AtomicStampedReference a2 = new AtomicStampedReference(1);
        a2.set(1,1);
        a2.set(2, 2);

        AtomicIntegerFieldUpdater<User> a4 = AtomicIntegerFieldUpdater.newUpdater(User.class, "id");
        a4.set(new User(), 1);
        a4.getAndIncrement(new User());


        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                    Thread.sleep(500);
                }
            }
        });
    }
}
