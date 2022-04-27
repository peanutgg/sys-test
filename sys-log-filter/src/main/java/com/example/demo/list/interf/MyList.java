package com.example.demo.list.interf;

public interface MyList<T> {
    void addLast(T t);

    T removeLast();

    T getInt(int index);


    T addIndex(int index,T t);

    int getSize();
}
