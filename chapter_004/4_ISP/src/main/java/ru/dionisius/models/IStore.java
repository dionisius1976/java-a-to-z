package ru.dionisius.models;

/**
 * Created by Dionisius on 12.01.2017.
 * Interface for all IStore classes.
 */
public interface IStore {
    /**
     * Adds specified item to this item's store of subitems.
     * @param item specified item.
     */
    void add(Item item);

    /**
     * Returns store of subitems of specified menu item.
     * @return store of subitems of specified menu item.
     */
    Item[] getStore();
}
