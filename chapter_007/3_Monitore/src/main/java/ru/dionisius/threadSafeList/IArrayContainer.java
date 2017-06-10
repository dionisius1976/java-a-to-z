package ru.dionisius.threadSafeList;


import java.util.Iterator;
import java.util.List;

/**
 * Created by Dionisius on 07.02.2017.
 */
public interface IArrayContainer<E> extends Iterable<E> {
    /**
     * Adds specified element to specified index of this list.
     * @param index specified index.
     * @param element specified element.
     */
    void add(int index, E element);

    /**
     * Returns the first index of specified element at this list.
     * @param e specified element at this list.
     * @return the index of specified element at this list.
     */
    int indexOf(E e);

    /**
     * Returns the last index of specified element at this list.
     * @param e specified element at this list.
     * @return the index of specified element at this list.
     */
    int lastIndexOf(E e);

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
    boolean contains(E e);

    /**
     * Returns the iterator of this list.
     * @return the iterator of this list.
     */
    Iterator<E> iterator();

    /**
     * Clears this list.
     */
    void clear();
    /**
     * Verifies if this list is empty.
     * @return true is this list is empty and false if not.
     */
    boolean isEmpty();

    /**
     * Returns sublist of this list from the controllers index to the end index without including it.
     * @param fromIndex the controllers index.
     * @param toIndex the end index.
     * @return the sublist of this list.
     */
    List<E> subList(int fromIndex, int toIndex);
}
