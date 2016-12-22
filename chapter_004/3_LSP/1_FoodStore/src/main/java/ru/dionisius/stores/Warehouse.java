package ru.dionisius.stores;

import ru.dionisius.wares.AFood;

/**
 * Created by Dionisius on 21.12.2016.
 */
public class Warehouse extends AStore implements IStore {
    /**
     * Array of food items.
     * It uses as store of food items.
     */
    private final AFood[] store = new AFood[50];
}
