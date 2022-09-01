package test.threadIntrupt;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class InteruptTest {
    public static volatile int count = 1;
    public static BlockingQueue<Runnable> l = new LinkedBlockingQueue<>();
    public static   final ExecutorService poll =
            new ThreadPoolExecutor(0,
                    1,
                    5000,
                    TimeUnit.MILLISECONDS,
                    l,
                    new ThreadFac(),
                    new RejectExe()
            );

    public static void main(String[] args) throws Exception {
        ParentThread t1 = new ParentThread();
        ParentThread t2 = new ParentThread();
        ParentThread t3 = new ParentThread();
       /* poll.

        t1.setT(t2);
        t2.setT(t3);
        t3.setT(t1);

        t1.setName("1");
        t2.setName("2");
        t3.setName("3");


        t1.start();
        t2.start();
        t3.start();*/
        poll.execute(new TestThread());
        poll.execute(new TestThread());



//        LockSupport.unpark(t1);


    }

    public static class ParentThread implements Runnable {
        private Thread t;

        public void setT(Thread t) {

            this.t = t;
        }

        @Override
        public void run() {

            try {
                LockSupport.park();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (; count <= 100; ) {
                if (count == 100) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + "-----" + count++);
                LockSupport.unpark(t);
                LockSupport.park();

            }
        }
    }


    public static class RejectExe implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            executor.execute(r);
        }
    }


    public static class ThreadFac implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    }


    public static class TestThread implements Runnable {
        @Override
        public void run() {

            System.out.println("�����̹߳����������");
        }
    }



}






