package ru.dionisius;

/**
 * Created by Dionisius on 10.02.2017.
 * Interface for SimpleStack class.
 */
public interface IStack<E> {

    /**
     * Tests if this stack is empty.
     * @return true if this stack is empty and false if not.
     */
    boolean empty();

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return the object at the top of this stack.
     */
    E peek();

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     * @return removed object.
     */
    E pop();

    /**
     * Pushes an specified item onto the top of this stack.
     * @param item specified item.
     * @return specified item.
     */
    E push(E item);

    /**
     * Returns the 1-based position where an specified object is on this stack.
     * @param o specified object.
     * @return the 1-based position where an specified object is on this stack.
     */
    int search(Object o);
}
