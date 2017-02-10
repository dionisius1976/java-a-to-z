package ru.dionisius;

import java.util.Iterator;

/**
 * Created by Dionisius on 10.02.2017.
 */
public class SimpleLinkedList<E> implements ILinkedContainer<E> {
    /**
     * The size of this list.
     */
    private int size = 0;
    /**
     * The first Node element of this list.
     */
    private Node<E> first;
    /**
     * The last Node element of this list.
     */
    private Node<E> last;

    /**
     * Constructs an empty list.
     */
    public SimpleLinkedList() {
    }

    @Override
    public void add(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            this.first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public E remove(E e) {
        E returnElement = null;
        if (e == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    this.unlink(x);
                    break;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (e.equals(x.item)) {
                    returnElement = x.item;
                    this.unlink(x);
                    break;                }
            }
        }
        return returnElement;
    }

    @Override
    public E remove(int index) {
        E returnElement = this.get(index);
        this.remove(returnElement);
        return returnElement;
    }

    @Override
    public E get(int index) {
        E returnElement = null;
        Node<E> x = this.first;
        for (int i = 0; i <= index; i++) {
            returnElement = x.item;
            x = x.next;
        }
        return returnElement;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>();
    }


    public int indexOf (E e) {
        int index = 0;
        int result  = -1;
        if (e == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    result = index;
                    break;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (e.equals(x.item)) {
                    result = index;
                    break;
                }
                index++;
            }
        }
        return result;
    }

    /**
     * Verifies does the specified element exist in this list.
     * @param e the specified element.
     * @return true if exist? false if not.
     */
    public boolean consists(E e) {
        return this.indexOf(e) != -1;
    }

    /**
     * Class that consists of E element and two references to previous and next Node classes in this list.
     * @param <E> type of this list elements.
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Iterator for this list.
     * @param <E> type of this list elements.
     */
    private class ListIterator<E> implements Iterator<E> {
        private Node currentNode = first;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return this.index++ < size;
        }

        @Override
        public E next() {
            E returnValue = (E) this.currentNode.item;
            this.currentNode = this.currentNode.next;
            return returnValue;
        }
    }

    /**
     * Returns the Node instance at specified index in this list.
     * @param index specified index in this list.
     * @return the Node instance.
     */
    private Node<E> node(int index) {
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    /**
     * Unlinks (remove) specified element at this list.
     * @param x specified element that removing.
     * @return removed element.
     */
    private E unlink(Node<E> x) {
        E element = x.item;
        Node<E> next = x.next;
        Node<E> prev = x.prev;

        if (prev == null) {
            this.first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        this.size--;
        return element;
    }
}
