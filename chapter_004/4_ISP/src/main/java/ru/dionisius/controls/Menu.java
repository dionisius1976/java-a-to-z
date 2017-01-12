package ru.dionisius.controls;

import ru.dionisius.items.Item;

/**
 * Created by Dionisius on 12.01.2017.
 */
public class Menu implements IMenu {
    private int indent = 0;

    @Override
    public void show(Item item) {
        for (int i = 0; i < item.getStore().length; i++) {
            if (item.getStore()[i] != null) {
                this.printIndent(indent);
                System.out.println(item.getStore()[i].getName());
                if (!this.isStoreEmpty(item.getStore()[i])) {
                    this.indent++;
                    this.show(item.getStore()[i]);
                    this.indent--;
                }
            }
        }
    }

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

    private void printIndent(int indent) {
        for (int i = 0; i <= indent ; i++) {
            System.out.print("--");
        }
    }
}
