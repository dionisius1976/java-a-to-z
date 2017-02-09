package ru.dionisius;


import java.util.Iterator;
import java.util.List;

/**
 * Created by Dionisius on 07.02.2017.
 */
public interface IArrayContainer<E> extends Iterable<E> {
    /**
     * Verifies if this list is empty.
     * @return true is this list is empty and false if not.
     */
    boolean isEmpty();

    /**
     * Returns sublist of this list from the start index to the end index without including it.
     * @param fromIndex the start index.
     * @param toIndex the end index.
     * @return the sublist of this list.
     */
    List<E> subList(int fromIndex, int toIndex);
}
