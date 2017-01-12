package ru.dionisius.stores;

/**
 * Created by Dionisius on 12.01.2017.
 * Class for menu item.
 */
public class Item extends AStore implements IItemAction {
    /**
     * This item's name.
     */
    private final String name;

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

    /**
     * Getter for item's name.
     * @return item's name.
     */
    public String getName() {
        return name;
    }
}
