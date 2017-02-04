package ru.dionisius;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 04.02.2017.
 * Test class for SimpleArray class.
 */
public class SimpleArrayTest {
    /**
     * String for tests.
     */
    private final String testString = "TestString";
    /**
     * SimpleArray instance for tests.
     */
    private final SimpleArray<String> simpleArray = new SimpleArray(10);
    /**
     * Checks if add() method adds the specified object in SimpleArray instance.
     */
    @Test
    public void whenAddingObjectToSimpleArrayThenThisObjectIsInSimpleArray() {
        String expectedString = this.testString;
        this.simpleArray.add(this.testString);
        String resultString = this.simpleArray.get(0);
        assertThat(expectedString, is(resultString));
    }
    /**
     * Checks if delete() method deletes the specified object at specified position in SimpleArray instance.
     */
    @Test
    public void whenDeletingObjectFromSimpleArrayAtSpecifiedPositionThenThereIsNullAtThisPosition() {
        String expectedString = null;
        this.simpleArray.add(this.testString);
        this.simpleArray.delete(0);
        String resultString = this.simpleArray.get(0);
        assertThat(resultString, is(expectedString));
    }
    /**
     * Checks if delete() method throws ArrayIndexOutOfBoundsException when specified position is negative.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenDeletingObjectInSimpleArrayAtNegativePositionThenThrowsArrayIndexOutOfBoundsException() {
        this.simpleArray.delete(-1);
    }
    /**
     * Checks if delete() method throws ArrayIndexOutOfBoundsException when specified position is more then array size.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenDeletingObjectInSimpleArrayAtPositionMoreThenArrayLengthThenThrowsArrayIndexOutOfBoundsException() {
        this.simpleArray.delete(11);
    }
    /**
     * Checks if update() method updates the specified object at specified position in SimpleArray instance.
     */
    @Test
    public void whenUpdatingObjectInSimpleArrayAtSpecifiedPositionThenThereIsTheNewObjectAtThisPosition() {
        String expectedString = "UpdaredString";
        this.simpleArray.add(this.testString);
        this.simpleArray.update(0, expectedString);
        String resultString = this.simpleArray.get(0);
        assertThat(resultString, is(expectedString));
    }
    /**
     * Checks if update() method throws ArrayIndexOutOfBoundsException when specified position is negative.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenUpdatingObjectInSimpleArrayAtNegativePositionThenThrowsArrayIndexOutOfBoundsException() {
        this.simpleArray.update(-1, "AAA");
    }
    /**
     * Checks if update() method throws ArrayIndexOutOfBoundsException when specified position is more then array size.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenUpdatingObjectInSimpleArrayAtPositionMoreThenArrayLengthThenThrowsArrayIndexOutOfBoundsException() {
        this.simpleArray.update(11, "AAA");;
    }
    /**
     * Checks if get() method returns null from specified position  with null object in SimpleArray instance.
     */
    @Test
    public void whenGettingObjectInSimpleArrayAtSpecifiedPositionWithNullThenReturnsNull() {
        String expectedString = null;
        this.simpleArray.delete(0);
        String resultString = this.simpleArray.get(0);
        assertThat(expectedString, is(resultString));
    }
    /**
     * Checks if get() method throws ArrayIndexOutOfBoundsException when specified position is negative.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenGettingObjectInSimpleArrayAtNegativePositionThenThrowsArrayIndexOutOfBoundsException() {
        this.simpleArray.get(-1);
    }
    /**
     * Checks if get() method throws ArrayIndexOutOfBoundsException when specified position is more then array size.
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenGettingObjectInSimpleArrayAtPositionMoreThenArrayLengthThenThrowsArrayIndexOutOfBoundsException() {
        this.simpleArray.get(11);
    }
}