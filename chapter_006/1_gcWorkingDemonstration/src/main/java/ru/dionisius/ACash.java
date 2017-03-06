package ru.dionisius;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dionisius on 05.03.2017.
 * Abstract class for storing keys and according them soft referenced values.
 */
public abstract class ACash <K, V> {

    /**
     * Store for keys and according them soft referenced values.
     */
    private final Map<K, SoftReference<V>> cash = new HashMap<>();

    /**
     * Gets value from this cash according specified key.
     * If there is no value for this key or value for this key
     * is null then the value gets from getData() method.
     * @param key
     * @return
     */
    public V getValue (K key) {
        SoftReference<V> value = this.cash.get(key);
        if (value == null) {
            this.cash.put(key, new SoftReference<>(this.getData()));
        }
        return value.get();
    }

    /**
     * Gets the data from specified source.
     * @param <T> the type of data.
     * @return data.
     */
    protected abstract <T> T getData();
}
