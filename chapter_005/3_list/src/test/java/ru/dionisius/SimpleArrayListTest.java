package ru.dionisius;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 07.02.2017.
 * Test class for testing SimpleArrayList class.
 */
public class SimpleArrayListTest {
    /**
     * SimpleArrayList instance for tests.
     */
    private SimpleArrayList simpleArrayList;
    /**
     * String example for tests.
     */
    private final String teststring = "TestString";
    /**
     * Testing expected string.
     */
    private String expectedValue;
    /**
     * Testing result string.
     */
    private String resultValue;

    /**
     * Initiates the list and fill it.
     */
    @Before
    public void init(){
        this.simpleArrayList = new SimpleArrayList();
        for (int i = 0; i < 100; i++) {
            this.simpleArrayList.add(String.valueOf(i));
        }
    }

    /**
     * Tests if specified element is in this list after it is added there.
     */
    @Test
    public void whenObjectIsAddedThenThisObjectIsInTheList() {
        for (int i = 0; i < 100; i++) {
            expectedValue = String.valueOf(i);
            resultValue = String.valueOf(this.simpleArrayList.get(i));
            assertThat(expectedValue, is(resultValue));
        }
    }
    /**
     * Tests if specified element is in specified index in this list after it is added there.
     */
    @Test
    public void whenObjectIsAddedToSpecifiedIndexThenThisObjectIsAtSpecifiedIndexInTheList() {
        this.simpleArrayList.add(1,this.teststring);
        expectedValue = this.teststring;
        resultValue = String.valueOf(this.simpleArrayList.get(1));
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if IllegalArgumentException is thrown after attempt to add element to negative index.
     */
    @Test (expected = IllegalArgumentException.class)
    public void addMinus() {
        this.simpleArrayList = new SimpleArrayList();
        this.simpleArrayList.add(-1, this.teststring);
    }
    /**
     * Tests if IllegalArgumentException is thrown after attempt to add element to index that is more then list's size.
     */
    @Test (expected = IllegalArgumentException.class)
    public void addMax() {
        this.simpleArrayList = new SimpleArrayList();
        this.simpleArrayList.add(100, this.teststring);
    }
    /**
     * Tests if after removing lone specified element it is not exist in this list.
     */
    @Test
    public void whenLoneSpecifiedElementIsRemovedThenItIsNotExistInThisList() {
        this.simpleArrayList.remove("50");
        boolean expectedValue = false;
        boolean resultValue = this.simpleArrayList.contains("50");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if after removing element from specified position it is not exist at this position in this list.
     */
    @Test
    public void whenSpecifiedElementIsRemovedFromSpecifiedPositionThenItIsNotExistInThisPosition() {
        this.simpleArrayList.remove(50);
        boolean expectedValue = false;
        boolean resultValue = this.simpleArrayList.contains("50");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if get() method returns specified element from specified position in this list.
     */
    @Test
    public void whenSpecifiedElementIsInSpecifiedPositionThenGetReturnsItFromSpecifiedPosition() {
        Object expectedValue = "30";
        Object resultValue = this.simpleArrayList.get(30);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if set() method sets the specified element to specified position in this list.
     */
    @Test
    public void whenSpecifiedElementIsSetToSpecifiedPositionThenItIsInSpecifiedPosition() {
        String expectedValue = "222";
        this.simpleArrayList.set(1, expectedValue);
        String resultValue = String.valueOf(this.simpleArrayList.get(1));
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if indexOf() method returns right first index of specified element from this list.
     */
    @Test
    public void whenFirstIndexOfThenRightFirstIndexIsReturned() {
        int expectedValue = 0;
        int resultValue = this.simpleArrayList.indexOf(String.valueOf(expectedValue));
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if indexOf() method returns right last index of specified element from this list.
     */
    @Test
    public void whenLastIndexOfThenRightLastIndexIsReturned()  {
        int expectedValue = 99;
        int resultValue = this.simpleArrayList.lastIndexOf(String.valueOf(expectedValue));
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if subList() method returns right sublist of elements from this list.
     */
    @Test
    public void whenSubListThenRightSublisIsReturned() {
        List expectedValue = new ArrayList();
        for (int i = 10; i < 50; i++) {
            expectedValue.add(String.valueOf(i));
        }
        List resultValue = this.simpleArrayList.subList(10, 50);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if isEmpty() method returns false when this list is not empty.
     */
    @Test
    public void whenListIsNotEmptyThenIsEmptyIsFalse() {
        boolean expectedValue = false;
        boolean resultValue = this.simpleArrayList.isEmpty();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if isEmpty() method returns true when this list is empty.
     */
    @Test
    public void whenListIsEmptyThenIsEmptyIsTrue() {
        this.simpleArrayList = new SimpleArrayList(20);
        boolean expectedValue = true;
        boolean resultValue = this.simpleArrayList.isEmpty();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if size() method returns 0 when this list is empty.
     */
    @Test
    public void whenZeroElementsAreInListThenSizeIsZero() {
        this.simpleArrayList = new SimpleArrayList(20);
        int expectedValue = 0;
        int resultValue = this.simpleArrayList.size();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if size() method returns 10 when there is 10 elements added to this list.
     */
    @Test
    public void whenTenElementsAreInListThenSizeIsTen() {
        this.simpleArrayList = new SimpleArrayList(20);
        for (int i = 0; i < 10; i++) {
            this.simpleArrayList.add(this.teststring);
        }
        int expectedValue = 10;
        int resultValue = this.simpleArrayList.size();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if contains() method returns true when specified element is in this list.
     */
    @Test
    public void whenSpecifiedElementExistThenTrueReturned() {
        boolean expectedValue = true;
        boolean resultValue = this.simpleArrayList.contains("11");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if contains() method returns false when specified element is not in this list.
     */
    @Test
    public void whenSpecifiedElementIsNotExistThenFalseReturned() {
        boolean expectedValue = false;
        boolean resultValue = this.simpleArrayList.contains("555");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if iterator() method returns right iterator from this list.
     */
    @Test
    public void whenIteratorThenRightIteratorReturning() {
        SimpleArrayList expectedList = this.simpleArrayList;
        List resultList = new ArrayList();
        Iterator it = this.simpleArrayList.iterator();
        while (it.hasNext()) {
            resultList.add(it.next());
        }
        for (int i = 0; i < expectedList.size(); i++) {
            this.resultValue = String.valueOf(resultList.get(i));
            this.expectedValue = String.valueOf(expectedList.get(i));
            assertThat(expectedValue, is(resultValue));
        }
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if clear() method clears this list.
     */
    @Test
    public void whenClearThenThisListIsEmpty() {
        this.simpleArrayList.clear();
        int expectedValue = 0;
        int resultValue = this.simpleArrayList.size();
        assertThat(expectedValue, is(resultValue));
    }
}