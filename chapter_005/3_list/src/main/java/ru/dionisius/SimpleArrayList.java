package ru.dionisius;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dionisius on 07.02.2017.
 */
public class SimpleArrayList<E> implements IArrayContainer<E>, ILinkedContainer<E> {
    /**
     * Default capacity for default constructor.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Empty array for clear() method.
     */
    private static final Object[] EMPTY_ELEMENTS = {};
    /**
     * The size of this list.
     */
    private  int size = 0;
    /**
     * The capacity for this list.
     */
    private int capacity = this.DEFAULT_CAPACITY;
    /**
     * The array of elements for this list.
     */
    private Object[] elements;

    /**
     * Default constructor.
     */
    public SimpleArrayList() {
        this.elements = new Object[this.DEFAULT_CAPACITY];
    }

    /**
     * Constructor with initial SimpleArrayList size.
     * @param initialSize initial SimpleArrayList size.
     */
    public SimpleArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialSize);
        }
        this.capacity = initialSize;
        this.elements = new Object[initialSize];
    }

    @Override
    public void add(E e) {
        if (!this.isSpaceEnough()) {
            this.grow();
        }
        this.elements[this.size++] = e;
    }
    @Override
    public void add(int index, E element) {
        if (index < 0) {
            throw new IllegalArgumentException("Illegal index value!");
        }
        if (!this.isSpaceEnough()) {
            this.grow();
        }
        E[] temp = (E[]) new Object[this.capacity];
        System.arraycopy(this.elements,0, temp, 0, index);
        temp[index] = element;
        System.arraycopy(this.elements, index, temp, index + 1, this.capacity - index - 1);
        this.elements = temp;
        this.size++;
    }
    @Override
    public E remove(E e) {
        int index = this.indexOf(e);
        E returnElement = (E) this.elements[index];
        if (index != -1) {
            this.remove(index);
        }
        return  returnElement;
    }
    @Override
    public E remove(int index) {
        E removed = (E) this.elements[index];
        E[] temp = (E[]) new Object[this.capacity - 1];
        System.arraycopy(this.elements, 0, temp, 0, index);
        System.arraycopy(this.elements, index + 1, temp, index, this.size - index - 1);
        this.elements = temp;
        this.size--;
        return removed;
    }
    @Override
    public E get(int index) {
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        E oldElement = (E) this.elements[index];
        this.elements[index] = element;
        return oldElement;
    }
    @Override
    public int indexOf(Object o) {
        int returnIndex = -1;
        int lastIndex = this.elements.length - 1;
        if (o == null) {
            for (int i = 0; i <=lastIndex; i++) {
                if (this.elements[i] == null) {
                    returnIndex = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i <=lastIndex; i++) {
                if (o.equals(this.elements[i])) {
                    returnIndex = i;
                    break;
                }
            }
        }
        return returnIndex;
    }
    @Override
    public int lastIndexOf(Object o) {
        int returnIndex = -1;
        int lastIndex = this.elements.length - 1;
        if (o == null) {
            for (int i = lastIndex; i != 0 ; i--) {
                if (this.elements[i] == null) {
                    returnIndex = i;
                    break;
                }
            }
        } else {
            for (int i = lastIndex; i != 0; i--) {
                if (o.equals(this.elements[i])) {
                    returnIndex = i;
                    break;
                }
            }
        }
        return returnIndex;
    }
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List returningList = new ArrayList();
        for (int i = fromIndex; i < toIndex; i++) {
            returningList.add((E)this.elements[i]);
        }
        return returningList;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(E e) {
        return this.indexOf(e) >= 0;
    }

    @Override
    public Iterator iterator() {
        return new thisIterator();
    }

    @Override
    public void clear() {
        this.elements = this.EMPTY_ELEMENTS;
        this.size = 0;
        this.capacity = 0;
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
        int newCapacity = this.elements.length * 3 / 2;
        this.elements = Arrays.copyOf(this.elements, newCapacity);
        this.capacity++;
    }

    /**
     * Enlarges the capacity of this list.
     * @param additionalCapacity enlargement value.
     */
    private void grow(int additionalCapacity) {
        int newCapacity = this.elements.length + additionalCapacity;
        this.elements = Arrays.copyOf(this.elements, newCapacity);
        this.capacity = newCapacity;
    }

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
