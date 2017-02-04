package ru.dionisius;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 02.02.2017.
 * Testing class for CommonIterator class.
 */
public class CommonIteratorTest {
    /**
     * First testing array for iterator of iterators of integers.
     */
    private final int[] firstArray = {1, 2, 3};
    /**
     * Second testing array for iterator of iterators of integers.
     */
    private final int[] secondArray = {4, 5, 6};
    /**
     * Third testing array for iterator of iterators of integers.
     */
    private final int[] thirdArray = {7, 8, 9, 10};
    /**
     * Null testing array for iterator of iterators of integers.
     */
    private final int[] nullArray = null;
    /**
     * Empty testing array for iterator of iterators of integers.
     */
    private final int[] emptyArray = {};
    /**
     * First testing iterator of integers.
     */
    private final Iterator first = new testIterator(this.firstArray);
    /**
     * Second testing iterator of integers.
     */
    private final Iterator second = new testIterator(this.secondArray);
    /**
     * Third testing iterator of integers.
     */
    private final Iterator third = new testIterator(this.thirdArray);
    /**
     * Testing iterator of null array.
     */
    private final Iterator nullIterator = new testIterator(this.nullArray);
    /**
     * Testing iterator of empty array.
     */
    private final Iterator emptyIterator = new testIterator(this.emptyArray);
    /**
     * Testing iterator of arrays of integers.
     */
    private final Iterator[] itArray = {this.first, this.second, this.third};
    /**
     * Testing iterator of arrays of integers with null array.
     */
    private final Iterator[] itArrayWithNull = {this.nullIterator, this.second, this.third};
    /**
     * Testing iterator of arrays of integers with empty array.
     */
    private final Iterator[] itArrayWithEmpty = {this.first, this.emptyIterator, this.third};
    /**
     * Testing iterator of iterators of integers.
     */
    private final testSuperIterator superIterator = new testSuperIterator(this.itArray);
    /**
     * Testing iterator of iterators of integers with iterator of arrays of integers with null array.
     */
    private final testSuperIterator superIteratorWithNull = new testSuperIterator(this.itArrayWithNull);
    /**
     * Testing iterator of iterators of integers with iterator of arrays of integers with empty array.
     */
    private final testSuperIterator superIteratorWithEmpty = new testSuperIterator(this.itArrayWithEmpty);
    /**
     * CommonIterator instance.
     */
    private final CommonIterator ci = new CommonIterator();
    /**
     * Result iterator of integers received after conversion of iterator of iterators of integers.
     */
    private final Iterator resultIterator = ci.convert(this.superIterator);
    /**
     * Result iterator of integers received after conversion of iterator of iterators of integers with null array.
     */
    private final Iterator resultIteratorWithNull = ci.convert(this.superIteratorWithNull);
    /**
     * Result iterator of integers received after conversion of iterator of iterators of integers with empty array.
     */
    private final Iterator resultIteratorWithEmpty = ci.convert(this.superIteratorWithEmpty);
    /**
     * Iterator class for array of integers.
     */
    private class testIterator implements Iterator {
        /**
         * Iterator for array of integers.
         */
        private final int[] array;
        /**
         * Index for current array value.
         */
        private int index = 0;

        /**
         * Constructor.
         * @param array specified array of integers.
         */
        private testIterator(final int[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return this.array != null && this.index < this.array.length && this.index >= 0;
        }

        @Override
        public Object next() {
            Object returningObject = new Object();
            if (this.array != null && this.array.length != 0) {
                returningObject = this.array[this.index];
                if (this.hasNext()) {
                    this.index++;
                } else {
                    this.index = -1;
                }
            }
            return returningObject;
        }
    }
    /**
     * Iterator class for iterators of arrays integers.
     */
    private class testSuperIterator implements Iterator{
        /**
         * Iterator for array of iterators of integers.
         */
        private final Iterator<Integer>[] it;
        /**
         * Index for current iterator in array of iterators of integers.
         */
        private int index = 0;

        /**
         * Constructor.
         * @param it specified array of iterators of integers.
         */
        private testSuperIterator(final Iterator<Integer>[] it) {
            this.it = it;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.it.length && this.index >= 0;
        }

        @Override
        public Object next() {
            Object returningObject = new Object();
            if (this.it != null && this.it.length != 0) {
                returningObject = this.it[this.index];
                if (this.hasNext()) {
                    this.index++;
                } else {
                    this.index = -1;
                }
            }
            return returningObject;
        }
    }
    /**
     * Tests if iterator of iterators of integers returns expected iterator of integers.
     */
    @Test
    public void whenIteratorOfIteratorsConvertedThenExpectedIteratorOfIntegers() {
        Integer[] expectedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List <Integer> resultList = new ArrayList();
        while (this.resultIterator.hasNext()) {
            resultList.add((int) this.resultIterator.next());
        }
        Integer[] resultArray = resultList.toArray(new Integer[resultList.size()]);
        assertThat(expectedArray, is(resultArray));
    }
    /**
     * Tests if iterator of iterators with null iterator returns expected iterator of integers.
     */
    @Test
    public void whenIteratorOfIteratorsWithNullIteratorConvertedThenExpectedIteratorOfIntegers() {
        Integer[] expectedArray = {4, 5, 6, 7, 8, 9, 10};
        List <Integer> resultList = new ArrayList();
        while (this.resultIteratorWithNull.hasNext()) {
            resultList.add((int) this.resultIteratorWithNull.next());
        }
        Integer[] resultArray = resultList.toArray(new Integer[resultList.size()]);
        assertThat(expectedArray, is(resultArray));
    }
    /**
     * Tests if iterator of iterators with empty iterator returns expected iterator of integers.
     */
    @Test
    public void whenIteratorOfIteratorsWithEmptyIteratorConvertedThenExpectedIteratorOfIntegers() {
        Integer[] expectedArray = {1, 2, 3, 7, 8, 9, 10};
        List <Integer> resultList = new ArrayList();
        while (this.resultIteratorWithEmpty.hasNext()) {
            resultList.add((int) this.resultIteratorWithEmpty.next());
        }
        Integer[] resultArray = resultList.toArray(new Integer[resultList.size()]);
        assertThat(expectedArray, is(resultArray));
    }
}