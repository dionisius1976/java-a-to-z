package ru.dionisius;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 01.02.2017.
 */
public class EvenArrayIteratorTest {
    /**
     * Testing array with evens.
     */
    private final int[] testArrayWithEvens = new int[] {3, 8, 2, 7, 9};
    /**
     * Testing array without evens.
     */
    private final int[] testArrayWithoutEvens = new int[] {1, 3, -1, 19, 101};
    /**
     * Testing empty array.
     */
    private final int[] testEmptyArray = new int[] {};
    /**
     * EvenArrayIterator instance for array with evens.
     */
    private final EvenArrayIterator itE = new EvenArrayIterator(this.testArrayWithEvens);
    /**
     * EvenArrayIterator instance for array without evens.
     */
    private final EvenArrayIterator itWE = new EvenArrayIterator(this.testArrayWithoutEvens);
    /**
     * EvenArrayIterator instance for empty array.
     */
    private final EvenArrayIterator itEmpty = new EvenArrayIterator(this.testEmptyArray);
    /**
     * Tests if hasNext() method for array with evens returns true.
     */
    @Test
    public void whenArrayHasEvensThenHasNextReturnsTrue() {
        boolean expectedValue = true;
        boolean resultValue = this.itE.hasNext();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if hasNext() method for array with evens returns false when there is no more evens.
     */
    @Test
    public void whenArrayHasNoMoreEvensThenHasNextReturnsFalse() {
        boolean expectedValue = false;
        this.itE.next();
        boolean resultValue = this.itE.hasNext();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if hasNext() method for array with no evens returns false.
     */
    @Test
    public void whenArrayHasNoEvensThenHasNextReturnsFalse() {
        boolean expectedValue = false;
        boolean resultValue = this.itWE.hasNext();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if hasNext() method for empty array returns false.
     */
    @Test
    public void whenArrayIsEmptyThenHasNextReturnsFalse() {
        boolean expectedValue = false;
        boolean resultValue = this.itEmpty.hasNext();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if next() method for array with evens returns first even value.
     */
    @Test
    public void whenArrayHasEvensThenNextReturnsFirstEvenValue() {
        int expectedValue = 8;
        int resultValue = (int) this.itE.next();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if next() method for array with evens returns second even value after first even value.
     */
    @Test
    public void whenArrayHasEvensThenAfterFirstThenNextReturnsNextEvenValue() {
        int expectedValue = 2;
        this.itE.next();
        int resultValue = (int) this.itE.next();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if next() method for array with evens throws ArrayIndexOutOfBoundsException when there is no more even values.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenArrayHasNoMoreEvensThenTrowsArrayIndexOutOfBoundsException() {
        this.itE.next();
        this.itE.next();
        this.itE.next();
    }
    /**
     * Tests if next() method for array without evens throws ArrayIndexOutOfBoundsException.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenArrayHasNoEvensThenTrowsArrayIndexOutOfBoundsException() {
        this.itWE.next();
    }
    /**
     * Tests if next() method for empty array throws ArrayIndexOutOfBoundsException.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenArrayIsEmptyThenTrowsArrayIndexOutOfBoundsException() {
        this.itWE.next();
    }
}