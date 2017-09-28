package ru.dionisius.controls;

import ru.dionisius.model.IStore;
import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 23.12.2016.
 * Interface for all control classes.
 */
public interface IControl {

    /**
     * Separates food model to specified model depending on
     * this food instance life time.
     * @param stores specified array of model.
     * @param foods specified array of food model.
     */
    void sort(IStore[] stores, IFood[] foods);
}
