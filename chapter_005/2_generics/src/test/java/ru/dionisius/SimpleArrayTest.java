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
     * Checks if add() method adds the specified object to SimpleArray instance.
     */
    @Test
    public void whenAddingObjectToSimpleArrayThenThisObjectIsInSimpleArray() {
        String expectedString = this.testString;
        this.simpleArray.add(this.testString);
        String resultString = this.simpleArray.get(0);
        assertThat(expectedString, is(resultString));
    }
    /**
     * Checks if delete() method deletes the specified object in SimpleArray instance.
     */
    @Test
    public void whenDeletingObjectFromSimpleArrayAtSpecifiedPositionThenThereIsNullAtThisPosition() {
        String expectedString = null;
        this.simpleArray.add(this.testString);
        this.simpleArray.delete(this.testString);
        String resultString = this.simpleArray.get(0);
        assertThat(resultString, is(expectedString));
    }
    /**
     * Checks if update() method updates the specified object to a new specified object in SimpleArray instance.
     */
    @Test
    public void whenUpdatingObjectInSimpleArrayAtSpecifiedPositionThenThereIsTheNewObjectAtThisPosition() {
        String expectedString = "UpdatedString";
        this.simpleArray.add(this.testString);
        this.simpleArray.update(this.testString, expectedString);
        String resultString = this.simpleArray.get(0);
        assertThat(resultString, is(expectedString));
    }
    /**
     * Checks if get() method returns the expected object from SimpleArray instance.
     */
    @Test
    public void whenGettingExpectedObjectThenReturnsThisObject() {
        String expectedString = "Hello, world!";
        this.simpleArray.add(expectedString);
        String resultString = this.simpleArray.get(0);
        assertThat(expectedString, is(resultString));
    }
    /**
     * Checks if get() method returns null from specified position  with null object in SimpleArray instance.
     */
    @Test
    public void whenGettingObjectInSimpleArrayAtSpecifiedPositionWithNullThenReturnsNull() {
        String expectedString = null;
        String resultString = this.simpleArray.get(0);
        assertThat(expectedString, is(resultString));
    }
}