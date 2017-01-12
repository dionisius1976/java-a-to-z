package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 22.12.2016.
 */
public abstract class AVegetable extends AFood implements IFood {
    /**
     * Constructor.
     * @param name       this item's name.
     * @param createDate date of this item production.
     * @param expiryDate this item's expiry date.
     * @param price      this item's price.
     * @param discount   this item discount in percent.
     */
    public AVegetable(final String name,
                     final LocalDate createDate,
                     final LocalDate expiryDate,
                     final double price,
                     final int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
