package ru.dionisius.hashList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Dionisius on 16.02.2017.
 * Test class for HashList class.
 */
public class HashListTest {
    /**
     * HashList instance.
     */
    private IHashList<String, String> hList = new HashList<>();
    /**
     * First key for tests.
     */
    private String key1 = "testKey1";
    /**
     * Second key for tests.
     */
    private String key2 = "testKey2";
    /**
     * First value for tests.
     */
    private String value1 = "testValue1";
    /**
     * Second value for tests.
     */
    private String value2 = "testValue2";
    /**
     * Checks if added pair key-value is in this HashList.
     */
    @Test
    public void whenInsertKeyAndValueThenTheseKeyAndValueAreInHashList() {
        this.hList.insert(key1, value1);
        String expectedValue = this.value1;
        String resultValue = this.hList.get(key1);
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Checks if get() returns proper value when added key for this value is null.
     */
    @Test
    public void whenInsertNullThenGetReturnsNull() {
        this.hList.insert(null, this.value1);
        String expectedValue = this.value1;
        String resultValue = this.hList.get(null);
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Checks if get() returns null when its parameter is not exist in HashList.
     */
    @Test
    public void whenGetNonexistingKeyThenReturnsNull() {
        String expectedValue = null;
        String resultValue = this.hList.get("Zoo");
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Checks if deleted key is not exist in this HashList.
     */
    @Test
    public void whenDeleteTheKeyThenThisKeyIsNotExistInHashList() {
        this.hList.insert(key1, value1);
        this.hList.delete(key1);
        String expectedValue = null;
        String resultValue = this.hList.get("Zoo");
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Checks the proper work of thisHashSet iterator.
     */
    @Test
    public void whenIteratorThenItWorksProperly() {
        this.hList.insert(key1, value1);
        this.hList.insert(key2, value2);
        List<String> expectedList = new ArrayList<>();
        expectedList.add(value1);
        expectedList.add(value2);
        List<String> resultList = new ArrayList<>();
        Object value;
        Iterator it = this.hList.iterator();
        while (it.hasNext()) {
            value = it.next();
            if (value == null) {
                continue;
            }
            resultList.add(String.valueOf(value));
        }
        assertThat(resultList, is(expectedList));
    }
}