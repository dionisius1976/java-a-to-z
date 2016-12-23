package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 21.12.2016.
 * Class Milk.
 */
public class Milk extends AFood implements IFood {

    /**
     * Percent of fat in this milk instance.
     */
    private final double fatContent;

    /**
     * Constructor.
     * @param name this item's name.
     * @param createDate date of this item production.
     * @param expiryDate this item's expiry date.
     * @param price this item's price.
     * @param discount this item discount in percent.
     * @param fatContent percent of fat in this milk instance.
     */
    public Milk(final String name,
                final LocalDate createDate,
                final LocalDate expiryDate,
                final double price,
                final int discount,
                final double fatContent) {
        super(name, createDate, expiryDate, price, discount);
        this.fatContent = fatContent;
    }
    /**
     * Getter for this milk instance percent of fat.
     * @return this milk instance percent of fat.
     */
    public double getFatContent() {
        return this.fatContent;
    }
}
