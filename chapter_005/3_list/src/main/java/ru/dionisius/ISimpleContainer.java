package ru.dionisius;

/**
 * Created by Dionisius on 07.02.2017.
 * Allows all classes that implement this interface
 * adding and getting objects in their containers.
 */
public interface ISimpleContainer<E> extends Iterable<E>{
    void add(E e);
    E get(int index);
}
