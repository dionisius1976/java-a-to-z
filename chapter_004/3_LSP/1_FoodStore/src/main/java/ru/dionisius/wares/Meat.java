package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 21.12.2016.
 * Class Meat.
 */
public class Meat extends AFood {
    /**
     * Is this fish frozen.
     */
    private final boolean isFrozen;
    /**
     * Number of this meat category.
     */
    private final int category;

    /**
     * Constructor.
     * @param name this item's name.
     * @param createDate date of this item production.
     * @param expiryDate this item's expiry date.
     * @param price this item's price.
     * @param discount this item discount in percent.
     * @param isFrozen is this meat frozen.
     * @param category number of this meat category.
     */
    public Meat(final String name,
                final LocalDate createDate,
                final LocalDate expiryDate,
                final double price,
                final int discount,
                final boolean isFrozen,
                final int category) {
        super(name, createDate, expiryDate, price, discount);
        this.isFrozen = isFrozen;
        this.category = category;
    }
    /**
     * Getter for this meat instance if fish is frozen.
     * @return true if frozen and false if not.
     */
    public boolean isFrozen() {
        return this.isFrozen;
    }
    /**
     * Getter for this meat instance number of category.
     * @return this meat instance number of category.
     */
    public int getCategory() {
        return this.category;
    }
}
