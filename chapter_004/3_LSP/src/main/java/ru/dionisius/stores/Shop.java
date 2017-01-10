package ru.dionisius.stores;

import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 21.12.2016.
 */
public class Shop extends AStore implements IStore {
    /**
     * Array of food items.
     * It uses as store of food items.
     */
    private final IFood[] store = new IFood[50];

    @Override
    public boolean mayBeAdded(IFood food) {
        int percentOfLifeTime = food.getPercentOfLifeTime();
        return percentOfLifeTime >= 25 && percentOfLifeTime < 100;
    }
}
