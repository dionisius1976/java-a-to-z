package ru.dionisius.hashList;

import java.util.Iterator;

/**
 * Created by Dionisius on 16.02.2017.
 * Interface for all HashList classes.
 */
public interface IHashList<T, V> {
    /**
     * Inserts pair key-value to HashList.
     * @param key key object.
     * @param value value object.
     * @return true if pair was added, false if not.
     */
    boolean insert(T key, V value);

    /**
     * Returns value of specified key.
     * @param key specified key.
     * @return value of specified key.
     */
    V get(T key);

    /**
     * Deletes pair key-value by key from the HashList.
     * @param key key of key-value pair.
     * @return true, if delete was successful, false if not.
     */
    boolean delete(T key);

    /**
     * Returns iterator of array that contains key-value pairs.
     * @return iterator of array that contains key-value pairs.
     */
    Iterator iterator();
}
