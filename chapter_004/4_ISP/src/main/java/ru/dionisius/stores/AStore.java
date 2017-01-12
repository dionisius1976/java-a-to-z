package ru.dionisius.stores;

/**
 * Created by Dionisius on 12.01.2017.
 * Abstruct class for all AStore classes.
 */
public class AStore implements IStore {
    /**
     * Store for subitems.
     */
    private final Item[] store = new Item[20];
    /**
     * Index of current cell in store.
     */
    private int index = 0;

    @Override
    public void add(Item item) {
        if (this.index <=20) {
            this.store[this.index] = item;
            this.index++;
        }
    }

    @Override
    public Item[] getStore() {
        return this.store;
    }
}
