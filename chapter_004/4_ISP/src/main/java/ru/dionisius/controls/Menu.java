package ru.dionisius.controls;


import ru.dionisius.model.Item;

/**
 * Created by Dionisius on 12.01.2017.
 */
public class Menu implements IMenu {
    /**
     * The printing indent for current menu item.
     */
    private int indent = -1;

    @Override
    public void show(Item item) {
        for (int i = 0; i < item.getStore().length; i++) {
            if (item.getStore()[i] != null) {
                this.printIndent(this.indent);
                System.out.println(item.getStore()[i].getName());
                if (!this.isStoreEmpty(item.getStore()[i])) {
                    this.indent++;
                    this.show(item.getStore()[i]);
                    this.indent--;
                }
            }
        }
    }

    /**
     * Checks if specified menu item has subitems.
     * @param item specified menu item.
     * @return true if specified menu item has subitems and false if not.
     */
    private boolean isStoreEmpty(Item item) {
        boolean isStoreEmpty = true;
        if (item != null) {
            for (Item i : item.getStore()) {
                if (i != null) {
                    isStoreEmpty = false;
                }
            }
        }
        return isStoreEmpty;
    }

    /**
     * Prints indent.
     * @param indent indent length.
     */
    private void printIndent(int indent) {
        for (int i = 0; i <= indent ; i++) {
            System.out.print("--");
        }
    }
}
