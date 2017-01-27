package ru.dionisius.models;

import ru.dionisius.wares.AVegetable;

/**
 * Created by Dionisius on 24.12.2016.
 */
public class Refrigerator extends Warehouse implements IStore {
    /**
     * Array of food models.
     * It uses as store of food models.
     */
    private final AVegetable[] store = new AVegetable[50];
    /**
     * Verifies if specified food item may be added to this storage.
     * @param food specified food item.
     * @return true if item can be added and false if not.
     */
    public boolean mayBeAdded(AVegetable food) {
        Warehouse wh = new Warehouse();
        return wh.mayBeAdded(food);
    }
}
