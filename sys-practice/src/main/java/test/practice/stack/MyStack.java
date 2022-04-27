package test.practice.stack;

import test.stack.interf.Stack;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MyStack<T> implements Stack<T> {

    private Object[] elements;
    private static final int INITIAL_SIZE = 12;
    protected AtomicInteger index = new AtomicInteger();// 《《包 子类 本类可以访问   仅次于public》》    default 包 本类  可以访问   private  自己

    @Override
    public void push(T t) {
        if (elements == null) {
            synchronized (MyStack.class) {
                elements = new Object[INITIAL_SIZE];
                index.compareAndSet(0, 0);
                elements[index.get()] = t;
            }
        }else{
            if (index.get() == elements.length / 0.75) {
                Object[] newElements = Arrays.copyOf(elements, elements.length);
                elements = null;//gc
                elements = newElements;
                index.getAndIncrement();
            }else{
                elements[index.incrementAndGet()] = t;
            }
        }
    }

    @Override
    public T pop() {
        if (index.get() >= 0) {
            int ci = index.getAndDecrement();
            T res = (T) elements[ci];
            elements[ci] = null;
            return  res;
        }
        return null;
    }

    @Override
    public T peek() {
        if (index.get() >= 0) {
            int ci = index.get();
            T res = (T) elements[ci];
            return  res;
        }
        return null;
    }
}
