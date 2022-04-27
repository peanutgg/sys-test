package com.example.demo.list.impl;

import com.example.demo.list.interf.MyList;

public class MyListImp<T> implements MyList<T> {
    private Node<T> head;
    private Node<T> tail;

    transient int size = 0;

    @Override
    public int getSize() {
        return size;
    }

    public MyListImp() {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public void addLast(T t) {
        if (head == null) {
            tail = head = new Node<T>(null, t, null);
            head.next = tail;
            tail.prev = head;
            size++;
        } else {
            Node<T> temp = tail;
            Node<T> newNode = new Node<T>(temp, t, head);
            temp.next = newNode;
            tail = newNode;
            size++;
        }

    }

    @Override
    public T removeLast() {
        Node<T> result = tail;
        tail.prev.next = null;
        size--;
        return result.data;
    }

    @Override
    public T getInt(int index) {
        return node(index).data;
    }

    @Override
    public T addIndex(int index, T t) {
        Node<T> temp = node(index);
        Node<T> newNode = new Node(temp.prev, t, temp);
        temp.prev = newNode;
        size++;
        return newNode.data;
    }

    public Node<T> node(int index) {
        if (index < (size >> 1)) {
            Node<T> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<T> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private static class Node<T> {
        Node<T> prev;
        T data;
        Node<T> next;

        protected Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

    }


    public static void main(String[] args) {
        MyList myList = new MyListImp();

        myList.addLast("1");
        myList.addLast("2");
        myList.addLast("3");
        myList.addLast("4");
        myList.addIndex(3,5);
        for (int i = 0; i < myList.getSize(); i++) {

            System.out.println(myList.getInt(i));

        }

    }
}
