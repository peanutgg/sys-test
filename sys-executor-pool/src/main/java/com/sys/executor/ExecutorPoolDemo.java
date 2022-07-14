package com.sys.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorPoolDemo {
    private int coreSize;
    private long keepAliveTime;
    private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private List<Worker> workers = new ArrayList<>();
    public static final int READY = 0;
    public static final int RUNNING = -1;
    public static final int STOP = 1;

    public static enum RunState {
        READY,
        RUNNING,
        STOP,
        ;
    }

    private final class Worker implements Runnable {
        private int state = 0;
        private Thread thread;
        private Runnable task;
        private int completeTasks = 0;

        public Worker(int state, Thread worker, Runnable task) {
            this.state = READY;
            this.task = task;
            this.thread = new Thread(this);
        }

        @Override
        public void run() {
            runWorker(task);
        }


        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Runnable getTask() {
            return task;
        }

        public void setTask(Runnable task) {
            this.task = task;
        }
    }

    public ExecutorPoolDemo(int coreSize, int keepAliveTime) {
        this.coreSize = coreSize;
        this.keepAliveTime = keepAliveTime;
    }

    public void init() {
        for (int i = 0; i < coreSize; i++) {
            workers.add(new Worker(0, new Thread(), null));
        }
    }

    public void execute(Runnable task) {
        tasks.add(task);
        for (int i = 0; i < workers.size(); i++) {

        }
    }

    public void runWorker(Worker woker) {
        Thread wokerThread = woker.thread;
        for (; ; ) {
            try {
                Runnable take = tasks.take();
                take.run();
                woker.completeTasks++;
            } catch (InterruptedException e) {
                System.out.println("获得任务失败...");
                e.printStackTrace();
            }
        }

    }

}
