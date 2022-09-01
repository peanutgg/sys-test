package test.Thread;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThread_Runnable implements Runnable{
    private static ThreadLocal<AtomicInteger> li = new ThreadLocal<>();
    static final CountDownLatch begin = new CountDownLatch(1);
    static final CountDownLatch end = new CountDownLatch(21);
    private Thread pre ;
    public TestThread_Runnable(Thread pre){
        this.pre = pre;
    }

    public Integer update() throws Exception{
        pre.join();
        for (int i = 0; i < 999; i++) {
            begin.await();
//            System.out.println("��ǰ�߳�id------:"+Thread.currentThread().getName()+"��ǰ�Ѿ����----:"+i);
            end.countDown();
            if (li.get() == null) {
                li.set(new AtomicInteger());
                li.get().getAndDecrement();
            }else{
                li.get().getAndDecrement();
            }
            if (i == 4) {
                System.out.println("�߳�id----:" + Thread.currentThread().getName() + "�Ѿ���ɣ�ThreadLocalֵΪ+"+li.get());
                end.await();
            }
        }

        return 0;
    }

    public void run() {
        try {
            update();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
