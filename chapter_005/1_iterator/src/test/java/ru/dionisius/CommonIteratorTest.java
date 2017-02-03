package ru.dionisius;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 02.02.2017.
 */
public class CommonIteratorTest {
    private final int[] firstArray = {1, 2, 3};
    private final int[] secondArray = {4, 5, 6};
    private final int[] thirdArray = {7, 8, 9, 10};
    private final int[] nullArray = null;
    private final int[] emptyArray = {};
    private final Iterator first = new testIterator(this.firstArray);
    private final Iterator second = new testIterator(this.secondArray);
    private final Iterator third = new testIterator(this.thirdArray);
    private final Iterator nullIterator = new testIterator(this.nullArray);
    private final Iterator emptyIterator = new testIterator(this.emptyArray);
    private final Iterator[] itArray = {this.first, this.second, this.third};
    private final Iterator[] itArrayWithNull = {this.emptyIterator, this.second, this.third};
    private final Iterator[] itArrayWithEmpty = {this.first, this.nullIterator, this.third};
    private final testSuperIterator superIterator = new testSuperIterator(this.itArray);
    private final testSuperIterator superIteratorWithNull = new testSuperIterator(this.itArrayWithNull);
    private final testSuperIterator superIteratorWithEmpty = new testSuperIterator(this.itArrayWithEmpty);
    private final CommonIterator ci = new CommonIterator();
    private final Iterator resultIterator = ci.convert(this.superIterator);
    private final Iterator resultIteratorWithNull = ci.convert(this.superIteratorWithNull);
    private final Iterator resultIteratorWithEmpty = ci.convert(this.superIteratorWithEmpty);

    private class testIterator implements Iterator {
        private final int[] array;
        private int index = 0;

        private testIterator(int[] array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.array.length && this.index >= 0;
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

    private class testSuperIterator implements Iterator{
        private final Iterator<Integer>[] it;
        private int index = 0;

        private testSuperIterator(Iterator<Integer>[] it) {
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

    @Test
    public void whenIteratorOfIteratorsConvertedThenExpectedIteratorOfIntegers() {
        Integer[] expectedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List <Integer> resultList = new ArrayList();
        while (this.resultIterator.hasNext()) {
            resultList.add((int) this.resultIterator.next());
        }
        Integer[] resultArray = resultList.toArray(new Integer[resultList.size()]);
        for (int i = 0; i < resultArray.length; i++) {
            System.out.println(resultArray[i]);
        }
        assertThat(expectedArray, is(resultArray));
    }

    @Test
    public void whenIteratorOfIteratorsWithNullIteratorConvertedThenExpectedIteratorOfIntegers() {
        Integer[] expectedArray = {4, 5, 6, 7, 8, 9, 10};
        List <Integer> resultList = new ArrayList();
        while (this.resultIteratorWithNull.hasNext()) {
            System.out.println( this.resultIteratorWithNull.next());
            resultList.add((int) this.resultIteratorWithNull.next());
        }
        Integer[] resultArray = resultList.toArray(new Integer[resultList.size()]);
        for (int i = 0; i < resultArray.length; i++) {
            System.out.println(resultArray[i]);
        }
        assertThat(expectedArray, is(resultArray));
    }

    @Test
    public void whenIteratorOfIteratorsWithEmptyIteratorConvertedThenExpectedIteratorOfIntegers() {
        Integer[] expectedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List <Integer> resultList = new ArrayList();
        while (this.resultIterator.hasNext()) {
            resultList.add((int) this.resultIterator.next());
        }
        Integer[] resultArray = resultList.toArray(new Integer[resultList.size()]);
        for (int i = 0; i < resultArray.length; i++) {
            System.out.println(resultArray[i]);
        }
        assertThat(expectedArray, is(resultArray));
    }

    @Test
    public void whenFirstIteratorOfSuperIteratorHasValuesThenHasNextReturnsTrue() {
        boolean expectedValue = true;
        boolean resultValue = this.resultIterator.hasNext();
        assertThat(expectedValue, is(resultValue));
    }
//    /**
//     * Tests if hasNext() method for array with evens returns false when there is no more evens.
//     */
//    @Test
//    public void whenArrayHasNoMoreEvensThenHasNextReturnsFalse() {
//        boolean expectedValue = false;
//        this.itE.next();
//        boolean resultValue = this.itE.hasNext();
//        assertThat(expectedValue, is(resultValue));
//    }
//    /**
//     * Tests if hasNext() method for array with no evens returns false.
//     */
//    @Test
//    public void whenArrayHasNoEvensThenHasNextReturnsFalse() {
//        boolean expectedValue = false;
//        boolean resultValue = this.itWE.hasNext();
//        assertThat(expectedValue, is(resultValue));
//    }
//    /**
//     * Tests if hasNext() method for empty array returns false.
//     */
//    @Test
//    public void whenArrayIsEmptyThenHasNextReturnsFalse() {
//        boolean expectedValue = false;
//        boolean resultValue = this.itEmpty.hasNext();
//        assertThat(expectedValue, is(resultValue));
//    }
//    /**
//     * Tests if next() method for array with evens returns first even value.
//     */
//    @Test
//    public void whenArrayHasEvensThenNextReturnsFirstEvenValue() {
//        int expectedValue = 8;
//        int resultValue = (int) this.itE.next();
//        assertThat(expectedValue, is(resultValue));
//    }
//    /**
//     * Tests if next() method for array with evens returns second even value after first even value.
//     */
//    @Test
//    public void whenArrayHasEvensThenAfterFirstThenNextReturnsNextEvenValue() {
//        int expectedValue = 2;
//        this.itE.next();
//        int resultValue = (int) this.itE.next();
//        assertThat(expectedValue, is(resultValue));
//    }
//    /**
//     * Tests if next() method for array with evens throws ArrayIndexOutOfBoundsException when there is no more even values.
//     */
//    @Test (expected = ArrayIndexOutOfBoundsException.class)
//    public void whenArrayHasNoMoreEvensThenTrowsArrayIndexOutOfBoundsException() {
//        this.itE.next();
//        this.itE.next();
//        this.itE.next();
//    }
//    /**
//     * Tests if next() method for array without evens throws ArrayIndexOutOfBoundsException.
//     */
//    @Test (expected = ArrayIndexOutOfBoundsException.class)
//    public void whenArrayHasNoEvensThenTrowsArrayIndexOutOfBoundsException() {
//        this.itWE.next();
//    }
//    /**
//     * Tests if next() method for empty array throws ArrayIndexOutOfBoundsException.
//     */
//    @Test (expected = ArrayIndexOutOfBoundsException.class)
//    public void whenArrayIsEmptyThenTrowsArrayIndexOutOfBoundsException() {
//        this.itWE.next();
//    }
}