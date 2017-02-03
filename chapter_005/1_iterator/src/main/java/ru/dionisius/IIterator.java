package ru.dionisius;

import java.util.Iterator;

/**
 * Created by Dionisius on 02.02.2017.
 * Interface for all IIterator classes.
 */
public interface IIterator {

    /**
     * Converts iterator of iterators in one common iterator.
     * @param it
     * @return
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
