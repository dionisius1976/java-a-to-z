package ru.dionisius;

/**
 * Created by Dionisius on 04.02.2017.
 * Interface for all Store classes.
 */
public interface IStore <T extends ABase> {
    /**
     * Adds specified object to the store.
     * @param object specified object.
     */
    void add(T object);

    /**
     * Updates old specified object in the store by new specified object.
     * @param oldObject old specified object in the store.
     * @param newObject new specified object.
     */
    void update(final T oldObject, final T newObject);

    /**
     * Deletes specified object from the store.
     * @param object specified object in the store.
     */
    void delete(final T object);
}
