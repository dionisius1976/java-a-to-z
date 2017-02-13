package ru.dionisius;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Dionisius on 13.02.2017.
 * This class expose how to accelerate add() method using sort and binary search.
 */
public class FastSimpleArraySet<E> implements ISet<E> {
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
    public FastSimpleArraySet() {
        this.elements = (E[]) new Object[this.DEFAULT_CAPACITY];
    }

    @Override
    public void add(E e) {
        if (e != null) {
            if (!this.isSpaceEnough()) {
                this.grow();
            }
            if (this.isUnique(e)) {
                this.add(this.getAddingPosition(e), e);
            }
        }
    }

    /**
     * Adds specified element to specified position of this list.
     * @param index specified position of this list.
     * @param element specified element.
     */
    private void add(int index, E element) {
        E[] temp = (E[]) new Object[this.capacity];
        System.arraycopy(this.elements,0, temp, 0, index);
        temp[index] = element;
        System.arraycopy(this.elements, index, temp, index + 1, this.capacity - index - 1);
        this.elements = temp;
        this.size++;
    }

    /**
     * Finds proper position to insert specified element according to its hashcode.
     * Uses binary search.
     * @param e specified element
     * @return position to insert specified element.
     */
    private int getAddingPosition(E e) {
        int hashCode = e.hashCode();
        int leftBorder = -1;
        int rightBorder = this.size;
        int middle;
        while (leftBorder < rightBorder - 1) {
            middle = (leftBorder + rightBorder) >> 1;
            if (this.elements[middle].hashCode() < hashCode) {
                leftBorder = middle;
            } else {
                rightBorder = middle;
            }
        }
        return rightBorder;
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
     * Using binary search checks if specified element is unique for this list.
     * @param e specified element.
     * @return true if specified element is unique for this list and false if not.
     */
    private boolean isUnique(E e) {
        boolean result = true;
        if (e == null) {
            result = false;
        } else {
            int hashCode = e.hashCode();
            int leftBorder = -1;
            int rightBorder = this.size;
            int middle;
            while (leftBorder < rightBorder - 1) {
                middle = (leftBorder + rightBorder) >> 1;
                if (this.elements[middle].hashCode() == hashCode) {
                    result = false;
                    break;
                }
                if (this.elements[middle].hashCode() < hashCode) {
                    leftBorder = middle;
                } else {
                    rightBorder = middle;
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
