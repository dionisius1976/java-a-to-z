package ru.dionisius.model;

import ru.dionisius.wares.AReproductFood;

/**
 * Created by Dionisius on 23.12.2016.
 */
public class Reproduction extends Trash implements IStore {
    /**
     * Array of food model.
     * It uses as store of food model.
     */
    private final AReproductFood[] store = new AReproductFood[50];
    /**
     * Verifies if specified food item may be added to this storage.
     * @param food specified food item.
     * @return true if item can be added and false if not.
     */
    public boolean mayBeAdded(AReproductFood food) {
        Trash trash = new Trash();
        return trash.mayBeAdded(food) && food.getCanReproduct();
    }
}
