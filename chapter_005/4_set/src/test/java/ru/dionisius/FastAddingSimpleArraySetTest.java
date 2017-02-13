package ru.dionisius;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Dionisius on 13.02.2017.
 */
public class FastAddingSimpleArraySetTest {
    /**
     * Testing instance for SimpleArraySet.
     */
    private final ISet<String> set = new FastSimpleArraySet<>();

    /**
     * Tests if all added elements are in this set.
     */
    @Test
    public void WhenFifteenElementsAreAddedThenTheyAreInSet() {
        String[] expectedArray = new String[15];
        String[] resultArray = new String[15];
        int index = 0;
        for (int i = 0; i < 15; i++) {
            this.set.add(String.valueOf(i));
            expectedArray[i] = String.valueOf(i);
        }
        Iterator it = this.set.iterator();
        while (it.hasNext()) {
            resultArray[index++] = String.valueOf(it.next());
        }
        assertArrayEquals(expectedArray, resultArray);
    }
    /**
     * Tests if only one of many unique elements is added in this set.
     */
    @Test
    public void whenTenTheSameElementsAreAddedThenOnlyOneIsInTheSet() {
        String[] expectedArray = {"0"};
        String[] resultArray = new String[1];
        int index = 0;
        for (int i = 0; i < 9; i++) {
            this.set.add(String.valueOf(index));
        }
        Iterator it = this.set.iterator();
        while (it.hasNext()) {
            resultArray[index++] = String.valueOf(it.next());
        }
        assertArrayEquals(expectedArray, resultArray);
    }

    /**
     * Tests if iterator of this set works properly.
     */
    @Test
    public void whenIteratorThenItWorksProperly() {
        String[] expectedArray = new String[15];
        String[] resultArray = new String[15];
        int index = 0;
        for (int i = 15; i < 0; i--) {
            this.set.add(String.valueOf(i));
            expectedArray[i] = String.valueOf(i);
        }
        Iterator it = this.set.iterator();
        while (it.hasNext()) {
            resultArray[index++] = String.valueOf(it.next());
        }
        assertArrayEquals(expectedArray, resultArray);
    }

}