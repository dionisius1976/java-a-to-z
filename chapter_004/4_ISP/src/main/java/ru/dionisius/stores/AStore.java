package ru.dionisius.stores;

/**
 * Created by Dionisius on 12.01.2017.
 */
public class AStore implements IStore {
    private final Item[] store = new Item[20];
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
