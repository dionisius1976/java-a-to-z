package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 09.01.2017.
 */
public class Carrot extends AVegetable {
    /**
     * Constructor.
     * @param name       this item's name.
     * @param createDate date of this item production.
     * @param expiryDate this item's expiry date.
     * @param price      this item's price.
     * @param discount   this item discount in percent.
     */
    public Carrot(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
