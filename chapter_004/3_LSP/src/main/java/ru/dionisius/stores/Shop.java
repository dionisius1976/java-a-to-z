package ru.dionisius.stores;

import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 21.12.2016.
 */
public class Shop implements IStore {
    /**
     * Array of food items.
     * It uses as store of food items.
     */
    private final IFood[] store = new IFood[50];

    @Override
    public IFood[] getStore() {
        return this.store;
    }

    @Override
    public void add(IFood food) {
        for (int index = 0; index < this.store.length; index++) {
            if (this.store[index] == null) {
                if (food.getPercentOfLifeTime() > 75) {
                    food.applyDiscount();
                }
                this.store[index] = food;
                break;
            }
        }
    }

    @Override
    public void delete(IFood food) {
        for (int index = 0; index < this.store.length; index++) {
            if (food.getName().equals(this.store[index].getName())) {
                this.store[index] = null;
            }
        }
    }

    @Override
    public IFood getByName(final String name) {
        IFood foundFood = null;
        if (name != null) {
            for (IFood food: this.store) {
                if (food != null) {
                    if (name.equals(food.getName())) {
                        foundFood = food;
                        break;
                    }
                }
            }
        }
        return foundFood;
    }

    @Override
    public boolean mayBeAdded(IFood food) {
        int percentOfLifeTime = food.getPercentOfLifeTime();
        return percentOfLifeTime >= 25 && percentOfLifeTime < 100;
    }
}
