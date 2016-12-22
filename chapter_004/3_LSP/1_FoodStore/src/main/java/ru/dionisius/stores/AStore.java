package ru.dionisius.stores;

import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 21.12.2016.
 * Abstract for all stores of food items.
 */
public abstract class AStore implements IStore {
    /**
     * Array of food items.
     * It uses as store of food items.
     */
    private final IFood[] store = new IFood[50];

    @Override
    public void add(IFood food) {
        for (int index = 0; index < this.store.length; index++) {
            if (this.store[index] == null) {
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
        IFood findetFood = null;
        if (name != null) {
            for (int index = 0; index < this.store.length; index++) {
                if (this.store[index] != null) {
                    if (name.equals(this.store[index].getName())) {
                        findetFood = this.store[index];
                    }
                }
            }
        }
        return findetFood;
    }

    @Override
    public boolean hasFreeSpace() {
        boolean hasFreeSpace = false;
        for (int index = 0; index < this.store.length; index++) {
            if (this.store[index] == null) {
                hasFreeSpace = true;
                break;
            }
        }
        return hasFreeSpace;
    }
}
