package ru.dionisius;

import java.util.LinkedList;

/**
 * Created by Dionisius on 04.02.2017.
 * Simple container for generics.
 */
public class SimpleArray<T> {
    /**
     * Array for objects.
     */
    private final Object[] array;
    /**
     * Index for the array for objects.
     */
    private int index = 0;

    /**
     * Constructor.
     * Creates the array of objects with specified size.
     * @param size specified size for array of objects.
     */
    public SimpleArray(final int size) {
        this.array = new Object[size];
    }

    /**
     * Adds specified object of specified type to the array of objects.
     * @param t specified object of specified type.
     */
    public void add(final T t) {
        this.array[this.index++] = t;
    }
    /**
     * Deletes the specified object in the array of objects.
     * @param object specified object that have to be deleted.
     */
    public void delete(final T object) {
        for (int i = 0; i < this.array.length; i++) {
            if (object == null) {
                break;
            }
            if (this.array[i] == null) {
                continue;
            }
            if (this.array[i].equals(object)) {
                this.array[i] = null;
            }
        }
    }

    /**
     * Updates specified old object in this array to specified new object.
     * @param oldObject specified old object in this array.
     * @param newObject specified new object.
     */
    public void update(final T oldObject, final T newObject) {
        for (int i = 0; i < this.array.length; i++) {
            if (oldObject == null) {
                if (this.array[i] == null) {
                    this.array[i] = newObject;
                    break;
                }
            }
            if (this.array[i] == null) {
                continue;
            }
            if (this.array[i].equals(oldObject)) {
                this.array[i] = newObject;
            }
        }
    }

    /**
     * Returns the specified object of specified type at specified position from the array of objects.
     * @param position specified position in the array of objects.
     * @param <T> specified type.
     * @return specified object of specified type.
     */
    public <T> T get(final int position) {
        return (T) this.array[position];
    }

}
