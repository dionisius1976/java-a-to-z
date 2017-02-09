package ru.dionisius;


import java.util.Iterator;
import java.util.List;

/**
 * Created by Dionisius on 07.02.2017.
 */
public interface IArrayContainer<E> extends Iterable<E> {
    E get(int index);
    E set(int index, E element);
    void add(int index, E element);
    E remove(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    List<E> subList(int fromIndex, int toIndex);
}
