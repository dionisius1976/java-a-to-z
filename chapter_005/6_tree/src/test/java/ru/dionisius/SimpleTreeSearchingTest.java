package ru.dionisius;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 22.02.2017.
 * Testing class for SimpleTreeSearching class.
 */
public class SimpleTreeSearchingTest {
    /**
     * SimpleTreeSearching instance for tests.
     */
    private SimpleTreeSearching<String> tree = new SimpleTreeSearching<>();
    private final Random random = new Random();
    private LinkedList<String> testList = new LinkedList<>();
    boolean resultValue = true;
    boolean expectedValue;
    String value;

    /**
     * Initial filling of this tree.
     */
    @Before
    public void init() {
        for (int i = 0; i < 30; i++) {
            value = String.valueOf(i);
            this.tree.addChild(value);
            this.testList.add(value);
        }
    }

    /**
     * Checks if one of filling values consists in this tree.
     */
    @Test
    public void whenValueIsConsistInTheTreeThenReturnsTrue() {
        for (int i = 0; i < 30; i++) {
            expectedValue = this.testList.contains(String.valueOf(i));
            assertThat(this.resultValue, is(expectedValue));
        }
    }

    /**
     * Checks if a value that is not one of filling values consists in this tree.
     */
    @Test
    public void whenValueIsNotConsistInTheTreeThenReturnsFalse() {
        boolean resultValue = false;
        boolean expectedValue = this.tree.consists("47");
        assertThat(resultValue, is(expectedValue));
    }
}