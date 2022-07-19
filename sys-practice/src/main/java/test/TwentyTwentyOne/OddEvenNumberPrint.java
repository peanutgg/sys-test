package test.TwentyTwentyOne;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenNumberPrint {
    public static void main(String[] args) {
        final int[] i = {0};
        Thread t2;
        Thread t1;
        Object lock = new Object();
        ReentrantLock rlock = new ReentrantLock();
        Condition c1 = rlock.newCondition();
        Semaphore tk = new Semaphore(1);
        t1 = new Thread(() -> {
//            synchronized (lock) {
            try {
                rlock.lock();
                System.out.println("t1获得锁开始执行");
                while (i[0] < 100) {
                    if (i[0] % 2 == 1) {
                        System.out.println(i[0] + "\r");
                        i[0]++;
                        c1.signal();
//                        lock.notify();
                        System.out.println("t1通知完成");
                    } else {
                        try {
                            System.out.println("t1 wait");
                            c1.await();
                            System.out.println("t1 被唤醒");
//                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
//            }
            } finally {
                rlock.unlock();
                System.out.println("t1 release");
            }
        });
        t2 = new Thread(() -> {
//            synchronized (lock) {
            try {
                rlock.lock();
                System.out.println("t2获得锁开始执行");
                while (i[0] < 100) {
                    if (i[0] % 2 == 0) {
                        System.out.println(i[0] + "\r");
                        i[0]++;
                        c1.signal();
//                        lock.notify();
                        System.out.println("t2通知完成");
                    } else {
                        try {
                            System.out.println("t2 wait");
                            c1.await();
                            System.out.println("t2 被唤醒");
//                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
//            }
            } finally {
                rlock.unlock();
                System.out.println("t2 release");
            }
        });
        t1.start();
        t2.start();
    }
}
