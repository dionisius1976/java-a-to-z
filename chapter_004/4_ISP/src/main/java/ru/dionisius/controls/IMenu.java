package ru.dionisius.controls;


import ru.dionisius.model.Item;

/**
 * Created by Dionisius on 12.01.2017.
 * Interface for all Menu classes.
 */
public interface IMenu {
    /**
     * SPrints specified table in tree type.
     * @param table specified table to print.
     */
    void show(Item table);
}
