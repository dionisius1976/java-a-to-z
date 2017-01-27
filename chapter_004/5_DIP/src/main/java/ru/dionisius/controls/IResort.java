package ru.dionisius.controls;

import ru.dionisius.models.IStore;

/**
 * Created by Dionisius on 12.01.2017.
 * Interface for all IResort classes.
 */
public interface IResort {
    /**
     * Removes wrong food models from spesified models array to right models of this array.
     * @param stores spesified models array to resort.
     */
    void resort(IStore[] stores);
}
