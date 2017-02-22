package ru.dionisius;

/**
 * Created by Dionisius on 22.02.2017.
 * Interface for all simple trees with searching method.
 */
public interface ISimpleTreeSearch<E extends Comparable> extends ISimpleTree<E> {
    /**
     * Checks if specified element exist in this tree.
     * @param element specified element.
     * @return true if consists and false if not.
     */
    boolean consists(E element);
}
