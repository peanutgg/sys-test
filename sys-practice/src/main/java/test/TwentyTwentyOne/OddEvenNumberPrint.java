package test.TwentyTwentyOne;

import java.util.concurrent.Semaphore;

public class OddEvenNumberPrint {
    public static void main(String[] args) {
        final int[] i = {0};
        Thread t2;
        Thread t1;
        Object lock = new Object();
        Semaphore tk = new Semaphore(1);
        t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1获得锁开始执行");
                while (i[0] < 100) {
                    if (i[0] % 2 == 1) {
                        System.out.println(i[0] + "\r");
                        i[0]++;

//                        lock.notify();
//                        System.out.println("t1通知完成释放锁");
                    }else{
                        try {
//                            System.out.println("t1 wait");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2获得锁开始执行");
                while (i[0] < 100) {
                    if (i[0] % 2 == 0) {
                        System.out.println(i[0] + "\r");
                        i[0]++;
                        lock.notify();
//                        System.out.println("t2通知完成释放锁");
                    }else{
                        try {
//                            System.out.println("t2 wait");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
