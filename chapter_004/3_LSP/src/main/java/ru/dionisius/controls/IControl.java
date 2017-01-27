package ru.dionisius.controls;

import ru.dionisius.models.IStore;
import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 23.12.2016.
 * Interface for all control classes.
 */
public interface IControl {

    /**
     * Separates food models to specified models depending on
     * this food instance life time.
     * @param stores specified array of models.
     * @param foods specified array of food models.
     */
    void sort(IStore[] stores, IFood[] foods);
}
