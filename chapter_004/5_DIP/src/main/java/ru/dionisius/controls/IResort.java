package ru.dionisius.controls;

import ru.dionisius.stores.IStore;

/**
 * Created by Dionisius on 12.01.2017.
 * Interface for all IResort classes.
 */
public interface IResort {
    /**
     * Removes wrong food items from spesified stores array to right stores of this array.
     * @param stores spesified stores array to resort.
     */
    void resort(IStore[] stores);
}
