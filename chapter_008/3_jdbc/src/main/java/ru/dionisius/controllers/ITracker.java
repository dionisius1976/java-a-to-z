package ru.dionisius.controllers;

import ru.dionisius.models.Item;

/**
 * Created by Dionisius on 18.05.2017.
 */
public interface ITracker {
//    /**
//     * Adds the new item to database.
//     * @param name the name of the new item.
//     * @param desc the description of the new item.
//     */
//    void addNewOrder(final String orderName, final String desc);
//
//    /**
//     * Updates specified item.
//     * @param id specified item's id.
//     * @param newName specified item's new name.
//     * @param newDesc specified item's new description.
//     * @return true if specified item exists.
//     */
//    boolean editItem(final long id, final String newName, final String newDesc);
//
//    /**
//     * Deletes specified item.
//     * @param id specified item's id.
//     * @return true if specified item exists.
//     */
//    boolean deleteItem(final long id);
//
//    /**
//     * Adds new comment to specified item.
//     * @param id
//     * @return true if specified item exists.
//     */
//    boolean addNewComment(final long id, final String comment);
//
//    /**
//     * @param id specified item's id.
//     * @return Statement object with information about founded items.
//     */
//    Statement getAllComments(final long id);
//
//    /**
//     * Returns all items from database.
//     * @return all items from database.
//     */
//    Statement getAllItems();
//
//    /**
//     * Finds the item by specified id.
//     * @param id specified item's id.
//     * @return Statement object with information about founded item.
//     */
//    Statement findById(final long id);
//
//    /**
//     * @param name specified item's name.
//     * @return Statement object with information about founded item.
//     */
//    Statement findByName(final String name);
//
//    /**
//     * @param desc specified item's description.
//     * @return Statement object with information about founded item.
//     */
//    Statement findByDesc(final String desc);
    void add(Item item);
    public Item[] getAll();
    void delete(Item item);
    Item findById(long id);
    void update(long id, String newName, String newDesc);

}
