package ru.dionisius;


import java.util.Iterator;

/**
 * Created by Dionisius on 11.02.2017.
 * Interfaxe for SimpleSet classes.
 */
public interface ISet <E> {
    /**
     * Adds specified element to the set.
     * Only unique elements can be adde to  this set.
     * @param e
     */
    void add(E e);

    /**
     * Returns the iterator for this set.
     * @return the iterator for this set.
     */
    Iterator<E> iterator();
}
