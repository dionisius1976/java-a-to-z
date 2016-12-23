package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 21.12.2016.
 * Abstract class for all food items.
 */
public abstract class AFood implements IFood {
    /**
     * This item's name.
     */
    private final String name;
    /**
     *  The date of this item production.
     */
    private final LocalDate createDate;
    /**
     * This item's expiry date.
     */
    private final LocalDate expiryDate;
    /**
     * This item's price.
     */
    private double price;
    /**
     * This item discount in percent.
     */
    private final int discount;

    /**
     * Constructor.
     * @param name this item's name.
     * @param createDate date of this item production.
     * @param expiryDate this item's expiry date.
     * @param price this item's price.
     * @param discount this item discount in percent.
     */
    public AFood(final String name,
                 final LocalDate createDate,
                 final LocalDate expiryDate,
                 final double price,
                 final int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getCreateDate() {
        return this.createDate;
    }

    @Override
    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getDiscount() {
        return this.discount;
    }

    @Override
    public void applyDiscount() {
        this.price = this.price - this.price * this.discount / 100;
    }

    @Override
    public int getPercentOfLifeTime() {
        LocalDate today = LocalDate.now();
        long daysAfterCreateDate = java.time.temporal.ChronoUnit.DAYS.between(this.createDate, today);
        long daysStorageLife = java.time.temporal.ChronoUnit.DAYS.between(this.createDate, this.expiryDate);
        return (int) (100 * daysAfterCreateDate / daysStorageLife);
    }

}
