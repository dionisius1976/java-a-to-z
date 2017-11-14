package ru.dionisius.service;

import ru.dionisius.model.Comment;
import ru.dionisius.model.Item;

/**
 * Created by Dionisius on 18.05.2017.
 */
public interface ITracker {
    /**
     * Adds the new item to database.
     * @param item specified item.
     */
     void addNewItem(final Item item);

    /**
     * Updates specified item.
     * @param id specified item's id.
     * @param newName specified item's new name.
     * @param newDesc specified item's new description.
     */
    void editItem(final long id, final String newName, final String newDesc);

    /**
     * Deletes specified item.
     * @param item specified item.
     */
    void deleteItem(final Item item);

    /**
     * Adds new comment to specified item.
     * @param id item's id.
     * @param newComment item's new comment.
     */
    void addNewComment(final long id, final String newComment);

    /**
     * @param id specified item's id.
     * @return all comments of specified item.
     */
    Comment[] getAllComments(final long id);

    /**
     * Returns all items from database.
     * @return all items from database.
     */
     Item[] getAllItems();

    /**
     * Finds the item by specified id.
     * @param id specified item's id.
     * @return founded item.
     */
    Item findById(long id);

    /**
     * @param name specified item's name.
     * @return all founded items with specified name.
     */
     Item[] findByName(final String name);

    /**
     * @param desc specified item's description.
     * @return all founded items with specified description.
     */
    Item[] findByDesc(final String desc);

}
