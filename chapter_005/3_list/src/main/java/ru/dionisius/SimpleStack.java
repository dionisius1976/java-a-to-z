package ru.dionisius;

/**
 * Created by Dionisius on 10.02.2017.
 */
public class SimpleStack<E> implements IStack<E> {
    /**
     * ILinkedContainer class instance.
     */
    private final ILinkedContainer<E> list = new SimpleLinkedList<>();

    @Override
    public boolean empty() {
        return this.list.size() == 0;
    }

    @Override
    public E peek() {
        return (this.list.get(0) == null) ? null : this.list.get(0);
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E push(E item) {
        return null;
    }

    @Override
    public int search(Object o) {
        return 0;
    }
}
