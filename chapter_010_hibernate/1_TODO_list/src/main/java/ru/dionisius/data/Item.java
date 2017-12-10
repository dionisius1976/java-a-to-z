package ru.dionisius.data;

import java.sql.Timestamp;

/**
 * Created by Dionisius on 05.08.2017.
 * POJO.
 * This class stores task's ru.dionisius.ru.dionisius.data
 * and has getters and setters for this ru.dionisius.ru.dionisius.data.
 */
public class Item {
    /**
     * User's id.
     */
    private long id;
    /**
     * User's login.
     */
    private String desc;
    /**
     * User's email.
     */
    private boolean done;
    /**
     * The date of user's entry creation.
     */
    private Timestamp createDate;

    /**
     * Setter for id of this item.
     * @param id id of this item.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter for description of this item.
     * @param desc the description of this item.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter for "is done" state for this item.
     * @param done "is done" state for this item.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Setter for creation date for this item.
     * @param createDate creation date for this item.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for id of this item.
     * @return the id of this item.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for description of this item.
     * @return the description of this item.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter for "is done" state for this item.
     * @return "is done" state for this item.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Getter for creation date for this item.
     * @return the creation date for this item.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }
}
