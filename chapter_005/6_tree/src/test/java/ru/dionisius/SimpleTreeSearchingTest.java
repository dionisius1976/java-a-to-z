package ru.dionisius;

import org.junit.Before;
import org.junit.Test;

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
    ISimpleTreeSearch<String> tree = new SimpleTreeSearching<>();

    /**
     * Initial filling of this tree.
     */
    @Before
    public void init() {
        for (int i = 0; i < 100; i++) {
            this.tree.addChild(String.valueOf(i));
        }
    }

    /**
     * Checks if one of filling values consists in this tree.
     */
    @Test
    public void whenValueIsConsistInTheTreeThenReturnsTrue() {
        boolean resultValue = true;
        boolean expectedValue = this.tree.consists("67");
        assertThat(resultValue, is(expectedValue));
    }

    /**
     * Checks if a value that is not one of filling values consists in this tree.
     */
    @Test
    public void whenValueIsNotConsistInTheTreeThenReturnsFalse() {
        boolean resultValue = false;
        boolean expectedValue = this.tree.consists("167");
        assertThat(resultValue, is(expectedValue));
    }
}