package ru.dionisius.controls;

import ru.dionisius.models.Refrigerator;
import ru.dionisius.models.Reproduction;
import ru.dionisius.wares.AReproductFood;
import ru.dionisius.wares.AVegetable;

/**
 * Created by Dionisius on 23.12.2016.
 */
public class ExtControlQuality extends ControlQuality implements IControl {
    /**
     * Separates food models to specified models.
     * Depending on this food reproducing ability and life time.
     * @param stores specified array of models.
     * @param foods specified array of food models.
     */
    public void sort(Reproduction[] stores, AReproductFood[] foods) {
        for (AReproductFood food: foods) {
            for (Reproduction store: stores) {
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
    /**
     * Separates food models to specified models.
     * Depending on this food type and life time.
     * @param stores specified array of models.
     * @param foods specified array of food models.
     */
    public void sort(Refrigerator[] stores, AVegetable[] foods) {
        super.sort(stores, foods);
    }
}
