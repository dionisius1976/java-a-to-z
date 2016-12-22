package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 21.12.2016.
 * Class Fish.
 */
public class Fish extends AFood {

    /**
     * Is it sea fish.
     */
    private final boolean isSeaFish;
    /**
     * Is this fish frozen.
     */
    private final boolean isFrozen;

    /**
     * Constructor.
     * @param name this item's name.
     * @param createDate date of this item production.
     * @param expiryDate this item's expiry date.
     * @param price this item's price.
     * @param discount this item discount in percent.
     * @param isSeaFish is it sea fish.
     * @param isFrozen is this fish frozen.
     */
    public Fish(final String name,
                final LocalDate createDate,
                final LocalDate expiryDate,
                final double price,
                final int discount,
                final boolean isSeaFish,
                final boolean isFrozen) {
        super(name, createDate, expiryDate, price, discount);
        this.isSeaFish = isSeaFish;
        this.isFrozen = isFrozen;
    }

    /**
     * Getter for this fish instance if it is sea fish.
     * @return true if if it is sea fish and false if not.
     */
    public boolean isSeaFish() {
        return this.isSeaFish;
    }
    /**
     * Getter for this fish instance if fish is frozen.
     * @return true if frozen and false if not.
     */
    public boolean isFrozen() {
        return this.isFrozen;
    }
}
