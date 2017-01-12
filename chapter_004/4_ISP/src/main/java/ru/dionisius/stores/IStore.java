package ru.dionisius.stores;

/**
 * Created by Dionisius on 12.01.2017.
 */
public interface IStore {
    void add(Item item);
    Item[] getStore();
}
