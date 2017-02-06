package ru.dionisius;

/**
 * Created by Dionisius on 04.02.2017.
 */
public interface IStore <T extends ABase> {
    void add(T object);
    void update(final T oldObject, final T newObject);
    void delete(final T object);
}
