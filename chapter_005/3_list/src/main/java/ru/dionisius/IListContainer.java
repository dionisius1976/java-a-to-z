package ru.dionisius;


import java.util.Iterator;

/**
 * Created by Dionisius on 07.02.2017.
 */
public interface IListContainer<E> extends Iterable<E> {
    /**
     * Adds specified element to this list.
     * @param e specified element.
     */
    void add(E e);

    /**
     * Adds specified element to specified index of this list.
     * @param index specified index.
     * @param element specified element.
     */
    void add(int index, E element);

    /**
     * Removes specified element from this list.
     * @param e specified element.
     */
    void remove (E e);

    /**
     * Removes an element from specified index of this list.
     * @param index specified index of this list.
     * @return removed element.
     */
    E remove(int index);

    /**
     * Returns an element from specified index of this array.
     * @param index specified index of this array.
     * @return an element from specified index of this array.
     */
    E get(int index);

    /**
     * Returns the first index of specified element at this list.
     * @param o specified element at this list.
     * @return the index of specified element at this list.
     */
    int indexOf(Object o);

    /**
     * Returns the last index of specified element at this list.
     * @param o specified element at this list.
     * @return the index of specified element at this list.
     */
    int lastIndexOf(Object o);

    /**
     * Sets the new specified element to specified index at this list.
     * @param index specified index at this list.
     * @param element new specified element.
     * @return the old element that was at specified index at this list.
     */
    E set(int index, E element);

    /**
     * Returns the size of this list.
     * @return the size of this list.
     */
    int size();

    /**
     * Verifies if specified element exists at this list.
     * @param e specified element.
     * @return true if specified element exists ans false if not.
     */
    boolean contains (E e);

    /**
     * Returns the iterator of this list.
     * @return the iterator of this list.
     */
    Iterator<E> iterator();

    /**
     * Clears this list.
     */
    void clear();
}
