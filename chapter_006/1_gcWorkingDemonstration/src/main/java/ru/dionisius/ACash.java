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
    private final Map<K, SoftReference<V>> cash = new HashMap<K, SoftReference<V>>();

    /**
     * Gets value from this cash according specified key.
     * If there is no value for this key or value for this key
     * is null then the value gets from getData() method.
     * @param key
     * @return
     */
    public V getValue (K key) {
        V data = null;
        SoftReference<V> value = this.cash.get(key);
        if (value == null) {
            value = new SoftReference<V>(this.getData());
            this.cash.put(key, value);
        } else {
            data = value.get();
        }
        return data;
    }

    /**
     * Gets the data from specified source.
     * @param <T> the type of data.
     * @return data.
     */
    protected abstract <T> T getData();
}
