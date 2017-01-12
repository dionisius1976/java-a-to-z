package ru.dionisius.controls;

import ru.dionisius.stores.IStore;
import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 23.12.2016.
 * Interface for all control classes.
 */
public interface IControl {

    /**
     * Separates food items to specified stores depending on
     * this food instance life time.
     * @param stores specified array of stores.
     * @param foods specified array of food items.
     */
    void sort(IStore[] stores, IFood[] foods);
}
