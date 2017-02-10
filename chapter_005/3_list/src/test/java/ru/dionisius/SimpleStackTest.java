package ru.dionisius;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 10.02.2017.
 */
public class SimpleStackTest {
    /**
     * ILinkedContainer class instance.
     */
    private final IStack stack = new SimpleStack();

    /**
     * Checks if empty() method returns true if this stack is empty.
     */
    @Test
    public void whenListIsTrueThenEmptyReturnsTrue() {
        boolean expectedValue = true;
        boolean resultValue = this.stack.empty();
        assertThat(resultValue, is(expectedValue));
    }

    /**
     * Checks if empty() method returns true if this stack is empty.
     */
    @Test
    public void whenListIsNotTrueThenEmptyReturnsFalse() {
        boolean expectedValue = false;
        this.stack.push("Test");
        boolean resultValue = this.stack.empty();
        assertThat(resultValue, is(expectedValue));
    }

    /**
     * Tests if peek() removes the object at the top of this stack and returns that object.
     */
    @Test
    public void peek() {
        String expectedValue = "Test";
        this.stack.push(expectedValue);
        String resultValue = String.valueOf(this.stack.peek());
        assertThat(resultValue, is(expectedValue));
    }

    /**
     * Tests if pop() method returns the object at the top of this stack.
     */
    @Test
    public void whenSpecifiedElementIsInTheListThenPopReturnItAndDeleteItFromTheList() {
        String expectedValue = "Test";
        this.stack.push(expectedValue);
        String resultValue = String.valueOf(this.stack.pop());
        assertThat(resultValue, is(expectedValue));
        boolean expected = true;
        boolean result = this.stack.empty();
        assertThat(result, is(expected));
    }

    /**
     * Checks if true is returnrd when specified element is in the stack.
     */
    @Test
    public void whenSpecifiedElementExistThenTrueReturnd() {
        String expectedValue = "Test";
        this.stack.push(expectedValue);
        int expected = 0;
        int result = this.stack.search(expectedValue);
        assertThat(result, is(expected));
    }

    /**
     * Checks if false is returned when specified element is in this stack.
     */
    @Test
    public void whenSpecifiedEleyymentExistThenTrueReturnd() {
        String expectedValue = "Test";
        this.stack.push("Hello");
        int expected = -1;
        int result = this.stack.search(expectedValue);
        assertThat(result, is(expected));
    }
}