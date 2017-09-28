package ru.dionisius.controls;

import ru.dionisius.model.IStore;

/**
 * Created by Dionisius on 12.01.2017.
 * Interface for all IResort classes.
 */
public interface IResort {
    /**
     * Removes wrong food model from spesified model array to right model of this array.
     * @param stores spesified model array to resort.
     */
    void resort(IStore[] stores);
}
