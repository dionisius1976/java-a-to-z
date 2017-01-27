package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 21.12.2016.
 * Interface for all food models.
 */
public interface IFood {

    /**
     * Getter for this instance name.
     * @return  this instance name.
     */
    String getName();

    /**
     * Getter for this instance date of creation.
     * @return this instance date of creation.
     */
    LocalDate getCreateDate();

    /**
     * Getter for this instance expiry date.
     * @return this instance expiry date.
     */
    LocalDate getExpiryDate();

    /**
     * Getter for this instance price.
     * @return this instance price.
     */
    double getPrice();

    /**
     * Getter for this instance discount percent value.
     * @return this instance discount percent value.
     */
    int getDiscount();

    /**
     * Applies discount to this item's price and decrease this item's price.
     */
    void applyDiscount();

    /**
     * Calculates percent of life time of this product.
     * @return calculated life time of this product.
     */
    int getPercentOfLifeTime();
}
