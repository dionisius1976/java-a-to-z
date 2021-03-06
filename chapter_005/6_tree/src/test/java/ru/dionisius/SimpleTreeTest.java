package ru.dionisius;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 22.02.2017.
 * Testing class for testing SimpleTree class.
 */
public class SimpleTreeTest {
    /**
     * SimpleTree instance for tests.
     */
    private ISimpleTree<String> tree = new SimpleTree<>();

    /**
     *  Checks if all added childrens are in the tree.
     */
    @Test
    public void whenChildrensAreAddedThenTheyAreAllInTheTree() {
        boolean resultValue = true;
        boolean expectedValue;
        for (int i = 0; i < 100; i++) {
            this.tree.addChild(String.valueOf(i));
        }
        List<String> childsList = this.tree.getChildren();
        for (String string : childsList) {
            expectedValue = childsList.contains(string);
            assertThat(resultValue, is(expectedValue));
        }
    }
}