package ru.dionisius;


import java.util.Iterator;

/**
 * Created by Dionisius on 07.02.2017.
 */
public interface IListContainer<E> extends Iterable<E> {
    void add(E e);
    void remove (E e);
    boolean isEmpty();
    int size();
    boolean contains (E e);
    Iterator<E> iterator();
    void clear();
}
