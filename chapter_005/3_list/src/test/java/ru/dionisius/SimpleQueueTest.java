package ru.dionisius;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Dionisius on 10.02.2017.
 * Test class for SimpleQueue class.
 */
public class SimpleQueueTest {
    /**
     * SimpleQueue instance for tests.
     */
    private final IQueue queue = new SimpleQueue();

    /**
     * Checks offer() and poll() methods.
     */
    @Test
    public void whenTenElementsAreAddedThenTheseTenElementsArePolled() {
        for (int i = 0; i < 10; i++) {
            this.queue.offer(String.valueOf(i));
        }
        for (int i = 0; i < 10; i++) {
            assertThat(this.queue.poll(), is(String.valueOf(i)));
        }
    }

    /**
     * Checks element() method.
     */
    @Test
    public void whenSpecifiedElementIsAddedThenThisElementIsReturned() {
        String expectedValue = "Test";
        this.queue.offer(expectedValue);
        String resultValue = String.valueOf(this.queue.element());
        assertThat(resultValue, is(expectedValue));
    }

    /**
     * Checks if NoSuchElementException will be thrown if list is empty.
     */
    @Test (expected = NoSuchElementException.class)
    public void whenListIsEmptyThenNoSuchElementExceptionIsThrown() {
        this.queue.element();
    }

    /**
     * Tests if first element will be returned by peek() method.
     */
    @Test
    public void whenPeekThenFirstElementIsReturnrd() {
        String expectedValue = "Test";
        this.queue.offer(expectedValue);
        String resultValue = String.valueOf(this.queue.peek());
        assertThat(resultValue, is(expectedValue));
    }
}