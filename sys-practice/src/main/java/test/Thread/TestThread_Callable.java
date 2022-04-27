package test.Thread;

import java.util.concurrent.Callable;

public class TestThread_Callable implements Callable {
    private  ThreadLocal<Integer> li = new ThreadLocal<>();

    public Object call() throws Exception {
        return update();
    }

    public Integer update() {

        for (int i = 0; i < 200; i++) {
            li.set(i);
            System.out.println("当前li值："+li.get());
            System.out.println("线程名字" + Thread.currentThread().getName());
        }
        return li.get();
    }

}
