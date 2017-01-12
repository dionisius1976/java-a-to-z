package ru.dionisius.items;

/**
 * Created by Dionisius on 12.01.2017.
 */
public abstract class AStore implements IStore {

    private final Item[] items = new Item[20];
    private int index = 0;

    @Override
    public void add(Item item) {
        if (index <= 20) {
            this.items[this.index] = item;
            this.index++;
        }
    }

    @Override
    public Item[] getStore() {
        return this.items;
    }

}
