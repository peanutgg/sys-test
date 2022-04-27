package com.example.demo.stack.impl;

import com.example.demo.stack.interf.Stack;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class MyStack<T> implements Stack<T> {

    protected Object[] elementData;//default 为包访问权限
    //protected为当前包和其它包的子类
    protected int initialCapacity = 5;
    protected int elementCount = 0;
    private int modCount = 0;
    private int expectModCount = 0;
    public MyStack(){
        elementData = new Object[initialCapacity];
    }

    @Override
    public synchronized void push(T t) {
        if (elementData == null) {
            elementData = new Object[initialCapacity];
        }

        if(elementData[elementData.length-1]!=null){//满了
            modCount++;
            expectModCount = modCount;
            elementData = Arrays.copyOf(elementData, elementData.length+1);
            checkModify();
            elementData[elementCount++] = t;
        }else{
            modCount++;
            expectModCount = modCount;
            checkModify();
            elementData[elementCount++] = t;
        }
    }

    @Override
    public synchronized T pop() {
        T obj = peek();
        modCount++;
        elementData[elementCount - 1] = null;//gc
        elementCount--;
        return obj;
    }

    @Override
    public synchronized T peek() {
        T obj = (T)elementData[elementCount - 1];
        return obj;
    }

    final void checkModify(){
        if (modCount != expectModCount) {
            new ConcurrentModificationException();
        }
    }

    public static void main(String[] args) {

    }
}
