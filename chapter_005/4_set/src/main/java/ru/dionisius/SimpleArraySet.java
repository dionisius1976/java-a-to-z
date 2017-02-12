package ru.dionisius;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Dionisius on 11.02.2017.
 * Set collection based on array.
 */
public class SimpleArraySet<E> implements ISet<E> {
    /**
     * Default capacity for default constructor.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * The size of this list.
     */
    private  int size = 0;
    /**
     * The capacity for this list.
     */
    private int capacity = this.DEFAULT_CAPACITY;
    /**
     * The array for this set's elements.
     */
    private E[] elements;

    /**
     * Default constructor.
     * Creates initial array for ten elements.
     */
    public SimpleArraySet() {
        this.elements = (E[]) new Object[this.DEFAULT_CAPACITY];
    }

    @Override
    public void add(E e) {
        if (e != null) {
            if (!this.isSpaceEnough()) {
                this.grow();
            }
            if (this.isUnique(e)) {
                this.elements[this.size++] = e;
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new thisIterator();
    }

    /**
     * Checks if there is enough space for adding one element to this list.
     * @return
     */
    private boolean isSpaceEnough() {
        return this.capacity - this.size >= 1;
    }
    /**
     * Enlarges the capacity of this list.
     */
    private void grow() {
        this.capacity += this.capacity >> 2;
        this.elements = Arrays.copyOf(this.elements, this.capacity);
    }

    /**
     * Checks if specified element is unique for this list.
     * @param e specified element.
     * @return true if specified element is unique for this list and false if not.
     */
    private boolean isUnique(E e) {
        boolean result = true;
        if (e == null) {
            result = false;
        } else {
            for (int i = 0; i < this.size; i++) {
                if (e.equals(this.elements[i])) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Iterator for this set.
     * @param <E> type of this set's elements.
     */
    private class thisIterator<E> implements Iterator<E> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return this.index != size;
        }

        @Override
        public E next() {
            return  (E) elements[this.index++];
        }
    }
}
