package ru.dionisius;

import ru.dionisius.controls.Menu;
import ru.dionisius.items.Item;

/**
 * Created by Dionisius on 12.01.2017.
 */
public class Run {
    public static void main(String[] args) {
        Item table = new Item("Table");
        Item first = new Item("First");
        Item second = new Item("Second");
        Item third = new Item("Third");
        Item second1 = new Item("Second1");
        Item second11 = new Item("Second11");
        Item second111 = new Item("Second111");
        Item second2 = new Item("Second2");
        Item second3 = new Item("Second3");
        Item second31 = new Item("Second31");

        second.add(second1);
        second.add(second2);
        second.add(second3);
        second1.add(second11);
        second11.add(second111);
        second3.add(second31);
        table.add(first);
        table.add(second);
        table.add(third);

        Menu menu = new Menu();
        menu.show(table);
    }
}
