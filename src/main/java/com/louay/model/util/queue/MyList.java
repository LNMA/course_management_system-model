package com.louay.model.util.queue;

public interface MyList<E> {

    void enqueue(E item);

    E dequeue();

    boolean isFull();

    int size();

    boolean isEmpty();
}
