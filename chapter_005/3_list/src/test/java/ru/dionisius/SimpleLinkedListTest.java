package ru.dionisius;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Dionisius on 10.02.2017.
 * The test class for SimpleLinkedList class.
 */
public class SimpleLinkedListTest {
    /**
     * The SimpleLinkedList instance for tests.
     */
    private SimpleLinkedList list = new SimpleLinkedList();

    /**
     * Initial filling the testing list.
     */
    @Before
    public void init() {
        for (int i = 0; i < 10; i++) {
            this.list.add(String.valueOf(i));
        }
    }

    /**
     * Verifies all added elements with expected elements.
     */
    @Test
    public void whenElemensAreAddedThenTheyAreInTheList() {
        for (int i = 0; i < 10; i++) {
            assertThat(list.get(i), is(String.valueOf(i)));
        }
    }

    /**
     * Checks if specified element exists in this list after removing.
     */
    @Test
    public void whenElementIsRemovedThenItIsNotExistInTheList() {
        this.list.remove("5");
        boolean expected = false;
        boolean result  = this.list.consists("5");
        assertThat(result, is(expected));
    }

    /**
     * Checks if specified element exists in this list after removing from specified position.
     */
    @Test
    public void whenAloneElementAtSpecifiedPositionRemovedThenItIsNotExistInTheList() {
        this.list.remove(3);
        boolean expected = false;
        boolean result  = this.list.consists("3");
        assertThat(result, is(expected));
    }

    /**
     * Checks if specified element returns after get() method from specified position.
     */
    @Test
    public void whenGetFromSpecifiedPositionThenExpectedElementIsReturned() {
        String expected = "7";
        String result  = String.valueOf(this.list.get(7));
        assertThat(result, is(expected));
    }

    /**
     *  Checks if right iterator returns after iterator() method.
     */
    @Test
    public void whenIterstorThenRightWorkingIteratorReturns() {
        int index = 0;
        Iterator it = this.list.iterator();
        while (it.hasNext()) {
            assertThat(this.list.get(index), is(it.next()));
            index++;
        }
    }
}