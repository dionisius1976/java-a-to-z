package ru.dionisius;

import java.util.Iterator;

/**
 * Created by Dionisius on 01.02.2017.
 * Iterator for evens in specified array.
 */
public class EvenArrayIterator implements Iterator {

    /**
     * Specified array for this iterator.
     */
    private final int[] array;
    /**
     * The index of current even value in specified array.
     */
    private int index;

    /**
     * Constructor.
     * @param array specified array for this iterator.
     */
    public EvenArrayIterator(final int[] array) {
        this.array = array;
        this.index = this.getIndex(array);
    }

    @Override
    public boolean hasNext() {
        boolean returnValue = false;
        for (int i = this.index + 1; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                returnValue = true;
                break;
            }
        }
        return returnValue;
    }

    @Override
    public Object next() {
        Object returningObject = this.array[this.index];
        boolean isLastEven = true;
        for (int i = this.index + 1; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                this.index = i;
                isLastEven = false;
                break;
            }
        }
        if (isLastEven) {
            this.index = -1;
        }
        return returningObject;
    }

    /**
     * Sets index at first even value in specified array.
     * @param array specified array.
     * @return index of the first even value in specified array.
     */
    private int getIndex(final int[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                index = i;
                break;
            }
        }
        return index;
    }
}
