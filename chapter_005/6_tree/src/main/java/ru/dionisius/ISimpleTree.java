package ru.dionisius;

import java.util.List;

/**
 * Created by Dionisius on 21.02.2017.
 */
public interface ISimpleTree<E extends Comparable> {
    void addChild(E value);
    List<E> getChildren();
}
