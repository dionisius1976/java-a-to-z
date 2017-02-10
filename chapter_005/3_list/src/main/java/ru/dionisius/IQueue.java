package ru.dionisius;

import java.util.NoSuchElementException;

/**
 * Created by Dionisius on 10.02.2017.
 * Interface for SimpleQueue class.
 */
public interface IQueue<E> {
    /**
     * Adds the specified element as the tail (last element) of this list.
     * @param e the specified element
     * @return true if element was added.
     */
    boolean offer(E e);
    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @return the head (first element) of this list.
     */
    E peek();
    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     *
     * @return the head of this list
     * @throws NoSuchElementException if this list is empty.
     */
    E element();
    /**
     * Retrieves and removes the head (first element) of this list.
     * @return the first removed element from this list.
     */
    E poll();
    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @return removed first (head) element.
     * @throws NoSuchElementException if this list is empty.
     */
    E remove();
}
