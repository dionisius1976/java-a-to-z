package ru.dionisius.wares;

import java.time.LocalDate;

/**
 * Created by Dionisius on 23.12.2016.
 */
public abstract class AReproductFood extends AFood implements IFood {
    /**
     * The reproducing ability flag.
     */
    private boolean canReproduct = false;

    /**
     * Constructor.
     * @param name specified name of instance.
     * @param createDate specified creation date of instance.
     * @param expiryDate specified expiry date of instance.
     * @param price specified price of instance.
     * @param discount specified discount of instance.
     * @param canReproduct specified reproducing ability of instance.
     */
    public AReproductFood(final String name,
                          final LocalDate createDate,
                          final LocalDate expiryDate,
                          final double price,
                          final int discount,
                          final boolean canReproduct) {
        super(name, createDate, expiryDate, price, discount);
        this.canReproduct = canReproduct;
    }
    /**
     * Verifies if this class instanse may be reproduced.
     * @return true if item can be reproduced and false if not.
     */
    public boolean getCanReproduct() {
        return this.canReproduct;
    }
}
