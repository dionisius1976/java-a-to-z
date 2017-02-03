package ru.dionisius;

import java.util.Iterator;

/**
 * Created by Dionisius on 01.02.2017.
 * Iterator for primes in specified array.
 */
public class PrimeIterator implements Iterator {
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
    public PrimeIterator(final int[] array) {
        this.array = array;
        this.index = this.getIndex(array);
    }

    @Override
    public boolean hasNext() {
        boolean returnValue = false;
        for (int i = this.index + 1; i < this.array.length; i++) {
            if (this.isPrime(array[i])) {
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
        if (this.isPrime(array[i])) {
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
     * Sets index at first prime value in specified array.
     * @param array specified array.
     * @return index of the first prime value in specified array.
     */
    private int getIndex(final int[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (this.isPrime(array[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Checks if specified value is prime.
     * @param value specified value.
     * @return true if specified value is prime and false if not.
     */
    private boolean isPrime (int value) {
        boolean isPrime = true;
        int temp;
        if (value < 2) {
            isPrime = false;
        } else {
            for (int i = 2; i <= value / 2; i++) {
                temp = value % i;
                if (temp == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }
}
