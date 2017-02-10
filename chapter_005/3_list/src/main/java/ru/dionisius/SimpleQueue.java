package ru.dionisius;

import java.util.NoSuchElementException;

/**
 * Created by Dionisius on 10.02.2017.
 */
public class SimpleQueue<E> implements IQueue<E> {
    /**
     * ILinkedContainer class instance.
     */
    private final ILinkedContainer<E> list = new SimpleLinkedList<>();

    @Override
    public boolean offer(E e) {
        this.list.add(e);
        return true;
    }


    @Override
    public E peek() {
        return (this.list.get(0) == null) ? null : this.list.get(0);
    }

    @Override
    public E element() {
        try {
            E removed = this.list.get(0);
            return removed;
        } catch (NullPointerException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E poll() {
        E removed = this.list.get(0);
        this.list.remove(0);
        return removed;
    }

    @Override
    public E remove() {
        try {
            E removed = this.list.get(0);
            this.list.remove(0);
            return removed;
        } catch (NullPointerException e) {
            throw new NoSuchElementException();
        }
    }
}
