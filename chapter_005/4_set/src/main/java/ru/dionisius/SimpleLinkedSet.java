package ru.dionisius;

import java.util.Iterator;

/**
 * Created by Dionisius on 11.02.2017.
 */
public class SimpleLinkedSet<E> implements ISet<E> {
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
     * Constructs an empty set.
     */
    public SimpleLinkedSet() {
        super();
    }

    @Override
    public void add(E e) {
        if (this.indexOf(e) == -1) {
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
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator<E>();
    }

    private int indexOf (E e) {
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
    private class SetIterator<E> implements Iterator<E> {
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


}
