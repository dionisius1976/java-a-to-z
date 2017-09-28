package ru.dionisius.controls;

import ru.dionisius.model.IStore;
import ru.dionisius.wares.IFood;

/**
 * Created by Dionisius on 21.12.2016.
 * Class ControlQuality.
 * Separates food model to specified model depending on
 * this food instance life time.
 */
public class ControlQuality implements IControl, IResort {

    @Override
    public void sort(IStore[] stores, IFood[] foods) {
        for (IFood food: foods) {
            for (IStore store: stores) {
                if (store == null) {
                    continue;
                }
                if (food != null && store.mayBeAdded(food)) {
                    store.add(food);
                    break;
                }
            }
        }
    }

    @Override
    public void resort(IStore[] stores) {
        IFood[] foodToMove = new IFood[50];
        IFood[] currentStore = null;
        int counter = 0;
        for (IStore store: stores) {
            if (store == null) {
                continue;
            }
            currentStore = store.getStore();
            for (IFood food: currentStore) {
                if (food != null) {
                    if (!store.mayBeAdded(food)) {
                        foodToMove[counter] = food;
                        counter++;
                        store.delete(food);
                    }
                }
            }
        }
        this.sort(stores, foodToMove);
    }
}
