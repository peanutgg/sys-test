package com.sys.executor;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AAA {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

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
