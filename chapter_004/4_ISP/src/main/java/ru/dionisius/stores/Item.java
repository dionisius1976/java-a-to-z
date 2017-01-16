package ru.dionisius.stores;

/**
 * Created by Dionisius on 12.01.2017.
 * Class for menu item.
 */
public class Item implements IStore, IItemAction {
    /**
     * This item's name.
     */
    private final String name;
    /**
     * Store for subitems.
     */
    private final Item[] store = new Item[20];
    /**
     * Index of current cell in store.
     */
    private int index = 0;

    /**
     * Default constructor.
     * @param name item's name.
     */
    public Item(String name) {
        this.name = name;
    }

    @Override
    public void make() {

    }

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


    /**
     * Getter for item's name.
     * @return item's name.
     */
    public String getName() {
        return name;
    }
}
