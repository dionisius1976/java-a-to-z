package ru.dionisius.stores;

import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 21.12.2016.
 * Interface for all stores that store food items.
 */
public interface IStore {

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
     * Verifies is there free space to add a new item.
     * @return true if there is free space and false if there is no free space.
     */
    boolean hasFreeSpace();
}
