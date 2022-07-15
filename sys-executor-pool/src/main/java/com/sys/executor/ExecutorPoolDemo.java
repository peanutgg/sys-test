package com.sys.executor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorPoolDemo {
    private int coreSize;
    private long keepAliveTime;
    public int maxThreadCount;
    private BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private List<Worker> workers = new ArrayList<>();
    public static final int READY = 0;
    public static final int RUNNING = -1;
    public static final int STOP = 1;
    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();
    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();
    private boolean closeFlag = false;

    public static enum RunState {
        READY,
        RUNNING,
        STOP,
        ;
    }

    @Data
    private final class Worker implements Runnable {
        private int state = 0;
        private Thread thread;
        private Runnable task;
        private int completeTasks = 0;
        private boolean coreFlag = false;

        public Worker(int state, Runnable task, boolean coreFlag) {
            this.state = READY;
            this.task = task;
            this.thread = new Thread(this);
        }

        public Worker(int state, Runnable task) {
            this.state = READY;
            this.task = task;
            this.thread = new Thread(this);
            this.coreFlag = true;
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

    public ExecutorPoolDemo(int coreSize, int keepAliveTime) {
        this.coreSize = coreSize;
        this.keepAliveTime = keepAliveTime;
    }

    public void execute(Runnable task) {
        putLock.lock();
        try {
            //添加任务的时候创建线程
            if (workers.size() < coreSize) {
                addWorker(new Worker(READY, task));
            } else {
                tasks.add(task);
            }
        } finally {
            putLock.unlock();
        }
    }

    private void addWorker(Worker worker) {
        workers.add(worker);
        if (worker.state != RUNNING) {
            Thread t = worker.thread;
            //启动woker线程
            t.start();
        }
    }

    public void runWorker(Worker woker) {
        Thread wokerThread = woker.thread;
        while (tasks.peek() != null) {
            try {
                Runnable task = tasks.take();
                task.run();
                woker.completeTasks++;
            } catch (InterruptedException e) {
                System.out.println("获得任务失败...");
                e.printStackTrace();
            }
        }
    }
}
