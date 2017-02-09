package ru.dionisius;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
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
     * String for tests.
     */
    private final String teststring = "TestString";

    /**
     * Tests if specified element is in this list after it is added there.
     */
    @Test
    public void whenObjectIsAddedThenThisObjectIsInTheList() {
        this.simpleArrayList = new SimpleArrayList();
        this.simpleArrayList.add(this.teststring);
        String expectedValue = this.teststring;
        String resultValue = String.valueOf(this.simpleArrayList.get(0));
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if specified element is in specified index in this list after it is added there.
     */
    @Test
    public void whenObjectIsAddedToSpecifiedIndexThenThisObjectIsAtSpecifiedIndexInTheList() {
        this.simpleArrayList = new SimpleArrayList(10);
        this.simpleArrayList.add(0, "000");
        this.simpleArrayList.add(1, "111");
        this.simpleArrayList.add(2, "333");
        this.simpleArrayList.add(1,this.teststring);
        String expectedValue = this.teststring;
        String resultValue = String.valueOf(this.simpleArrayList.get(1));
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
        this.simpleArrayList = new SimpleArrayList(10);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.remove(this.teststring);
        Object expectedValue = null;
        Object resultValue = this.simpleArrayList.get(0);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if after removing element from specified position it is not exist at this position in this list.
     */
    @Test
    public void whenSpecifiedElementIsRemovedFromSpecifiedPositionThenItIsNotExistInThisPosition() {
        this.simpleArrayList = new SimpleArrayList(15);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.remove(1);
        Object expectedValue = "222";
        Object resultValue = this.simpleArrayList.get(1);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if get() method returns specified element from specified position in this list.
     */
    @Test
    public void whenSpecifiedElementIsInSpecifiedPositionThenGetReturnsItFromSpesifiedPosition() {
        this.simpleArrayList = new SimpleArrayList(5);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        Object expectedValue = "111";
        Object resultValue = this.simpleArrayList.get(1);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if set() method sets the specified element to specified position in this list.
     */
    @Test
    public void whenSpecifiedElementIsSetToSpecifiedPositionThenItIsInSpesifiedPosition() {
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.set(1, "222");
        Object expectedValue = "222";
        Object resultValue = this.simpleArrayList.get(1);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if indexOf() method returns right first index of specified element from this list.
     */
    @Test
    public void whenFirstIndexOfThenRightFirstIndexIsReturned() {
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
        int expectedValue = 2;
        int resultValue = this.simpleArrayList.indexOf("222");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if indexOf() method returns right last index of specified element from this list.
     */
    @Test
    public void whenLastIndexOfThenRightLastIndexIsReturned()  {
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
        int expectedValue = 3;
        int resultValue = this.simpleArrayList.lastIndexOf("111");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if subList() method returns right sublist of elements from this list.
     */
    @Test
    public void whenSubListThenRightSublisIsReturned() {
        this.simpleArrayList = new SimpleArrayList(30);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
        List expectedValue = new ArrayList();
        expectedValue.add("111");
        expectedValue.add("222");
        List resultValue = this.simpleArrayList.subList(1, 3);
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if isEmpty() method returns false when this list is not empty.
     */
    @Test
    public void whenListIsNotEmptyThenIsEmptyIsFalse() {
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
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
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
        boolean expectedValue = true;
        boolean resultValue = this.simpleArrayList.contains("111");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if contains() method returns false when specified element is not in this list.
     */
    @Test
    public void whenSpecifiedElementIsNotExistThenFalseReturned() {
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
        boolean expectedValue = false;
        boolean resultValue = this.simpleArrayList.contains("555");
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if iterator() method returns right iterator from this list.
     */
    @Test
    public void whenIteratorThenRightIteratorReturning() {
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
        List expectedValue = new ArrayList();
        for (int i = 0; i < this.simpleArrayList.size(); i++) {
            expectedValue.add(this.simpleArrayList.get(i));
        }
        List resultValue = new ArrayList();
        Iterator it = this.simpleArrayList.iterator();
        while (it.hasNext()) {
            resultValue.add(it.next());
        }
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Tests if clear() method clears this list.
     */
    @Test
    public void whenClearThenThisListIsEmpty() {
        this.simpleArrayList = new SimpleArrayList(20);
        this.simpleArrayList.add(this.teststring);
        this.simpleArrayList.add("111");
        this.simpleArrayList.add("222");
        this.simpleArrayList.add("111");
        this.simpleArrayList.clear();
        int expectedValue = 0;
        int resultValue = this.simpleArrayList.size();
        assertThat(expectedValue, is(resultValue));
    }
}