package ru.dionisius;

/**
 * Created by Dionisius on 09.02.2017.
 */
public interface ILinkedContainer<E> extends Iterable<E> {
    /**
     * Adds specified element to this list.
     * @param e specified element.
     */
    void add(E e);
    /**
     * Removes specified element from this list.
     * @param e specified element.
     * @return removed element.
     */
    E remove (E e);
    /**
     * Returns an element from specified index of this array.
     * @param index specified index of this array.
     * @return an element from specified index of this array.
     */
    E get(int index);
}
