package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 09.01.2017.
 */
public class Bones extends AReproductFood {
    /**
     * Constructor.
     * @param name specified name of instance.
     * @param createDate specified creation date of instance.
     * @param expiryDate specified expiry date of instance.
     * @param price specified price of instance.
     * @param discount specified discount of instance.
     * @param canReproduct specified reproducing ability of instance.
     */
    public Bones(String name,
                 LocalDate createDate,
                 LocalDate expiryDate,
                 double price,
                 int discount,
                 boolean canReproduct) {
        super(name, createDate, expiryDate, price, discount, canReproduct);
    }
}
