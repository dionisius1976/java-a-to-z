package ru.dionisius;

import java.util.List;

/**
 * Created by Dionisius on 21.02.2017.
 */
public interface ISimpleTree<E extends Comparable> {
    /**
     * Adds specified value to this tree.
     * @param value specified value.
     */
    void addChild(E value);

    /**
     * Returns list of all this tree's children.
     * @return list of all this tree's children.
     */
    List<E> getChildren();
}
