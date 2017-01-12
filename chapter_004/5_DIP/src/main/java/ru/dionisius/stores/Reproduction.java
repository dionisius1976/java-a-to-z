package ru.dionisius.stores;

import ru.dionisius.wares.AReproductFood;

/**
 * Created by Dionisius on 23.12.2016.
 */
public class Reproduction extends Trash implements IStore {
    /**
     * Array of food items.
     * It uses as store of food items.
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
