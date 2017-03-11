package ru.dionisius.threadSafeList;

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
     * Adds specified element to specified position of this list.
     * @param index specified position of this list.
     * @param element specified element.
     */
    void add(int index, E element);
    /**
     * Removes specified element from this list.
     * @param e specified element.
     * @return removed element.
     */
    E remove(E e);
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

    /** Returns the size of this list.
     * @return the size of this list.
     */
    int size();

    /**
     * Returns index of specified element in this list.
     * @param e specified element.
     * @return index of specified element in this list.
     */
    int indexOf(E e);
}
