package ru.dionisius;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by Dionisius on 23.02.2017.
 */
public class SimpleBinaryTreeTest {
    private SimpleBinaryTree<String> tree = new SimpleBinaryTree<>();
    private final Random random = new Random();
    private LinkedList<String> testList = new LinkedList<>();

    @Test
    public void addChild() throws Exception {
        boolean resultValue = true;
        boolean expectedValue;
        String randomValue = null;
        for (int i = 0; i < 30; i++) {
            randomValue = String.valueOf(this.random.nextInt(100));
            this.tree.addChild(randomValue);
            this.testList.add(randomValue);
        }
        for (int i = 0; i < 30; i++) {
            randomValue = String.valueOf(this.testList.peek());
            expectedValue = this.tree.consists(randomValue);
            assertThat(resultValue, is(expectedValue));
        }
    }
}