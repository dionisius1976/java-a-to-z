package ru.dionisius.model;

import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 24.12.2016.
 */
public abstract class AStore implements IStore {

    /**
     * Store of food instances.
     */
    private final IFood[] store = new IFood[20];

    @Override
    public IFood[] getStore() {
        return this.store;
    }

    @Override
    public void add(IFood food) {
        for (int index = 0; index < this.store.length; index++) {
            if (this.store[index] == null) {
                int percentOfLifeTime = food.getPercentOfLifeTime();
                if (percentOfLifeTime > 75 && percentOfLifeTime < 100) {
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
            if (this.store[index] != null) {
                if (food.getName().equals(this.store[index].getName())) {
                    this.store[index] = null;
                }
            }
        }
    }

    @Override
    public IFood getByName(String name) {
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
    public abstract boolean mayBeAdded(IFood food);
}
