package ru.dionisius.items;

/**
 * Created by Dionisius on 12.01.2017.
 */
public class Item extends AStore implements IItemAction {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public void make() {

    }

    public String getName() {
        return this.name;
    }
}
