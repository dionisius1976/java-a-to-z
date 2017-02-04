package ru.dionisius;

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
     * Deletes the object in specified position in the array of objects.
     * @param position specified position in the array of objects.
     */
    public void delete(final int position) {
        this.array[position] = null;
    }

    /**
     * Writes a new specified object of specified type in specified position to the array of objects.
     * @param position specified position in the array of objects.
     * @param t specified object of specified type.
     */
    public void update(final int position, final T t) {
        this.array[position] = t;
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
