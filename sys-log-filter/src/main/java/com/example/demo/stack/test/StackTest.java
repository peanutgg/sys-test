package com.example.demo.stack.test;

import com.example.demo.stack.impl.MyStack;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class StackTest {
    static MyStack myStack = new MyStack();
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        System.out.println("all threads is completed");
    });

    public static void main(String args[])  {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                testStack2(myStack);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    throw e;
                }
            });
        }
    }

    public static void testStack() {
        MyStack stack = new MyStack();
        for (int i = 0; i < 99; i++) {

            stack.push(i);
        }

        for (int i = 0; i < 99; i++) {

            System.out.print(stack.pop() + "\t");
            if (i % 9 == 0) {
                System.out.println();
            }
        }
    }

    public  static void  testStack2(MyStack stack) {
        for (int i = 0; i < 99; i++) {

            stack.push(i);
        }

        for (int i = 0; i < 99; i++) {

            System.out.print(stack.pop() + "\t");
            if (i % 9 == 0) {
                System.out.println();
            }
        }
    }
}
