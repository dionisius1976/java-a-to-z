package ru.dionisius.data;

import java.util.List;

/**
 * Created by Dionisius on 02.10.2017.
 */
public interface IDbManager {
    /**
     * Inserts specified item to database.
     * @param newItem specified item.
     */
    void createItem(Item newItem);

    /**
     * Returns all items from database.
     * @return all items from database.
     */
    List<Item> getAll();

    /**
     * Returns all performed items from database.
     * @return all performed items from database.
     */
    List<Item> getAllPerformed();
}
