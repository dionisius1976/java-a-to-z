package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 23.12.2016.
 */
public class Bread extends AReproductFood implements IFood {
    /**
     * Constructor.
     * @param name specified name of instance.
     * @param createDate specified creation date of instance.
     * @param expiryDate specified expiry date of instance.
     * @param price specified price of instance.
     * @param discount specified discount of instance.
     * @param canReproduct specified reproducing ability of instance.
     */
    public Bread(final String name,
                 final LocalDate createDate,
                 final LocalDate expiryDate,
                 final double price,
                 final int discount,
                 boolean canReproduct) {
        super(name, createDate, expiryDate, price, discount, canReproduct);
    }
}
