package ru.dionisius;

import ru.dionisius.stores.Shop;
import ru.dionisius.stores.Trash;
import ru.dionisius.stores.Warehouse;
import ru.dionisius.wares.IFood;

import java.time.LocalDate;

/**
 * Created by Dionisius on 21.12.2016.
 * Class ControlQuality.
 * Separates food items to specified stores depending on
 * this food instance life time.
 */
public class ControlQuality {
    /**
     * Warehouse store.
     */
    private Warehouse warehouse;
    /**
     * Shop store.
     */
    private Shop shop;
    /**
     * Trash store.
     */
    private Trash trash;
    /**
     * Date of today.
     */
    private final LocalDate today;

    /**
     * Constructor.
     * @param warehouse warehouse store.
     * @param shop shop store.
     * @param trash trash store.
     * @param today date of today.
     */
    public ControlQuality(Warehouse warehouse, Shop shop, Trash trash, LocalDate today) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
        this.today = today;
    }

    /**
     * Separates food items to specified stores depending on
     * this food instance life time.
     * @param food food instance.
     */
    public void sort(IFood food) {
        int percentsOfStorageLife = this.getPercentsOfStorageLife(food);
        if (percentsOfStorageLife >= 100) {
            this.trash.add(food);
        } else if (percentsOfStorageLife > 75
                && percentsOfStorageLife < 100) {
            food.applyDiscount();
            this.shop.add(food);
        } else if (percentsOfStorageLife >= 25
                && percentsOfStorageLife <= 75) {
            this.shop.add(food);
        } else {
            this.warehouse.add(food);
        }
    }

    /**
     * Calculate life time of food instance in percents.
     * @param food food instance.
     * @return life time of food instance in percents.
     */
    private int getPercentsOfStorageLife(IFood food) {
        long daysAfterCreateDate = java.time.temporal.ChronoUnit.DAYS.between(food.getCreateDate(), this.today);
        long daysStorageLife = java.time.temporal.ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return (int) (100 * daysAfterCreateDate / daysStorageLife);
    }
}
