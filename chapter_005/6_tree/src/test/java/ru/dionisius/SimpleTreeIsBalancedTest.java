package ru.dionisius;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 23.02.2017.
 * Test class for SimpleTreeIsBalanced class.
 */
public class SimpleTreeIsBalancedTest {
    /**
     * SimpleTreeIsBalanced instance for tests.
     */
    private SimpleTreeIsBalanced tree = new SimpleTreeIsBalanced();
    private BalancedTreeForTests<String> balancedTree = new BalancedTreeForTests<>();
    /**
     * Checks if the tree is balanced.
     */
    @Test
    public void whenTreeIsBalancedThenReturnTrue() {
        for (int i = 0; i < 5; i++) {
            this.balancedTree.addChild(String.valueOf(i));
        }
        boolean resultValue = true;
        boolean expectedValue = this.balancedTree.isBalanced();
        assertThat(expectedValue, is(resultValue));
    }
    /**
     * Checks if the tree is not balanced.
     */
    @Test
    public void whenTreeIsNotBalancedThenReturnFalse() {
        for (int i = 0; i < 10; i++) {
            this.tree.addChild(String.valueOf(i));
        }
        boolean resultValue = false;
        boolean expectedValue = this.tree.isBalanced();
        assertThat(expectedValue, is(resultValue));
    }
}