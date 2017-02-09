package ru.dionisius;

import java.util.ListIterator;

/**
 * Created by Dionisius on 09.02.2017.
 */
public interface ILinkedContainer<E> extends Iterable<E> {
    void addFirst(E e);
    void addLast(E e);
    E element();
    E getFirst();
    E getLast();
    ListIterator<E> listIterator(int index);
    boolean offer(E e);
    boolean	offerFirst(E e);
    boolean	offerLast(E e);
    E	peek();
    E	peekFirst();
    E	peekLast();
    E	poll();
    E	pollFirst();
    E	pollLast();
    E pop();
    void push(E e);
    E remove();
}
