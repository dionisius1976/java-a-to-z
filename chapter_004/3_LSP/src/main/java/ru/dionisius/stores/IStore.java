package ru.dionisius.stores;

import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 21.12.2016.
 * Interface for all stores that store food items.
 */
public interface IStore {

    /**
     * Returnes this instance's store of food items.
     * @return this instance's store of food items.
     */
    IFood[] getStore();

    /**
     * Adds a new item to this store.
     * @param food food item.
     */
    void add(IFood food);

    /**
     * Deletes specified food item.
     * @param food spesified food item.
     */
    void delete(IFood food);

    /**
     * Return specified by name item from store.
     * @param name the name of food item.
     * @return food item with specified name.
     */
    IFood getByName(String name);

    /**
     * Verifies if specified food item may be added to this storage.
     * @param food specified food item.
     * @return true if item can be added and false if not.
     */
    boolean mayBeAdded(IFood food);
}
