package ru.dionisius;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 01.02.2017.
 */
public class PrimeIteratorTest {
    /**
     * Testing array with evens.
     */
    private final int[] testArrayWithPrimes = new int[] {6, 2, 8, 7, 9};
    /**
     * Testing array without evens.
     */
    private final int[] testArrayWithoutPrimes = new int[] {4, 30, -1, 190, 1010};
    /**
     * Testing empty array.
     */
    private final int[] testEmptyArray = new int[] {};
    /**
     * EvenArrayIterator instance for array with evens.
     */
    private final PrimeIterator itP = new PrimeIterator(this.testArrayWithPrimes);
    /**
     * PrimeArrayIterator instance for array without evens.
     */
    private final PrimeIterator itWP = new PrimeIterator(this.testArrayWithoutPrimes);
    /**
     * PrimeArrayIterator instance for empty array.
     */
    private final PrimeIterator itEmpty = new PrimeIterator(this.testEmptyArray);
    /**
     * Tests if hasNext() method for array with primes returns true.
     */
    @Test
    public void whenArrayHasPrimeThenHasNextReturnsTrue() {
        boolean expectedValue = true;
        boolean resultValue = this.itP.hasNext();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if hasNext() method for array with primes returns false when there is no more evens.
     */
    @Test
    public void whenArrayHasNoMorePrimesThenHasNextReturnsFalse() {
        boolean expectedValue = false;
        this.itP.next();
        boolean resultValue = this.itP.hasNext();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if hasNext() method for array with no primes returns false.
     */
    @Test
    public void whenArrayHasNoPrimesThenHasNextReturnsFalse() {
        boolean expectedValue = false;
        boolean resultValue = this.itWP.hasNext();
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
     * Tests if next() method for array with primes returns first even value.
     */
    @Test
    public void whenArrayHasPrimesThenNextReturnsFirstEvenValue() {
        int expectedValue = 2;
        int resultValue = (int) this.itP.next();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if next() method for array with primes returns second even value after first even value.
     */
    @Test
    public void whenArrayHasPrimesThenAfterFirstThenNextReturnsNextEvenValue() {
        int expectedValue = 7;
        this.itP.next();
        int resultValue = (int) this.itP.next();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if next() method for array with primes throws ArrayIndexOutOfBoundsException when there is no more even values.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenArrayHasNoMorePrimesThenTrowsArrayIndexOutOfBoundsException() {
        this.itP.next();
        this.itP.next();
        this.itP.next();
    }
    /**
     * Tests if next() method for array without primes throws ArrayIndexOutOfBoundsException.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenArrayHasNoPrimesThenTrowsArrayIndexOutOfBoundsException() {
        PrimeIterator itWPt = new PrimeIterator(this.testArrayWithoutPrimes);
        itWPt.next();
    }
    /**
     * Tests if next() method for empty array throws ArrayIndexOutOfBoundsException.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenArrayIsEmptyThenTrowsArrayIndexOutOfBoundsException() {
        this.itWP.next();
    }

}