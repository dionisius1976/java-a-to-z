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
        E result = this.list.get(0);
        this.list.remove(0);
        return result;
    }

    @Override
    public E push(E item) {
        this.list.add(0, item);
        return item;
    }

    @Override
    public int search(E e) {
        return this.list.indexOf(e);
    }
}
