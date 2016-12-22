package ru.dionisius;

import org.junit.Test;
import ru.dionisius.stores.Shop;
import ru.dionisius.stores.Trash;
import ru.dionisius.stores.Warehouse;
import ru.dionisius.wares.Fish;
import ru.dionisius.wares.IFood;
import ru.dionisius.wares.Meat;
import ru.dionisius.wares.Milk;

import java.time.LocalDate;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Dionisius on 21.12.2016.
 * Test class for ControlQuality class.
 */
public class ControlQualityTest {
    /**
     * Warehouse instance.
     */
    private final Warehouse warehouse = new Warehouse();
    /**
     * Shop instance.
     */
    private final Shop shop = new Shop();
    /**
     * Trash instance.
     */
    private final Trash trash = new Trash();
    /**
     * ControlQuality instance.
     */
    private ControlQuality cq;
    /**
     * Date of today.
     */
    private LocalDate today;
    /**
     * Fish instance.
     */
    private final IFood fish = new Fish("Beluga",
                                        LocalDate.of(2016, 1, 1),
                                        LocalDate.of(2017, 1, 1),
                                        3200,
                                        30,
                                        false,
                                        true);
    /**
     * Meat instance.
     */
    private final IFood meat = new Meat("Cow",
                                        LocalDate.of(2016, 1, 1),
                                        LocalDate.of(2016, 1, 15),
                                        550,
                                        35,
                                        false,
                                        1);

    /**
     * Milk instance.
     */
    private final IFood milk = new Milk("Prostokvashino",
                                        LocalDate.of(2016, 1, 1),
                                        LocalDate.of(2016, 2, 1),
                                        50,
                                        25,
                                        3.5);

    /**
     * Tests if percent of Life time is less than 25% then food instance stores in Warehouse.
     */
    @Test
    public void whenPersentOfLifeTimeIsLessThenTwentyFiveThenFoodIsInWarehouseAndShopAndTrashAreEmpty() {
        this.today = LocalDate.of(2016, 2, 1);
        this.cq = new ControlQuality(this.warehouse, this.shop, this.trash, this.today);
        cq.sort(this.fish);
        IFood expectedFood = this.fish;
        IFood resultFood = this.warehouse.getByName(this.fish.getName());
        assertThat(expectedFood, is(resultFood));
        assertThat(null, is(this.shop.getByName(this.fish.getName())));
        assertThat(null, is(this.trash.getByName(this.fish.getName())));
    }

    /**
     * Tests if percent of Life time is less than 25% then food price is not changed.
     */
    @Test
    public void whenPercentOfLifeTimeIsLessThenTwentyFiveThenPriceIsTheSame() {
        this.today = LocalDate.of(2016, 2, 1);
        this.cq = new ControlQuality(this.warehouse, this.shop, this.trash, this.today);
        cq.sort(this.fish);
        double expectedPrice = this.fish.getPrice();
        double resultPrice = this.warehouse.getByName(this.fish.getName()).getPrice();
        assertThat(expectedPrice, is(resultPrice));
    }

    /**
     * Tests if percent of Life time is in range 25..75% then food instance stores in shop.
     */
    @Test
    public void whenPersentOfLifeTimeIsInRangeTwentyFiveSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty() {
        this.today = LocalDate.of(2016, 1, 7);
        this.cq = new ControlQuality(this.warehouse, this.shop, this.trash, this.today);
        cq.sort(this.meat);
        IFood expectedFood = this.meat;
        IFood resultFood = this.shop.getByName(this.meat.getName());
        assertThat(expectedFood, is(resultFood));
        assertThat(null, is(this.warehouse.getByName(this.meat.getName())));
        assertThat(null, is(this.trash.getByName(this.meat.getName())));
    }

    /**
     * Tests if percent of Life time is in range 25..75% then food price is not changed.
     */
    @Test
    public void whenPercentOfLifeTimeIsInRangeTwentyFiveSeventyFiveThenPriceIsTheSame() {
        this.today = LocalDate.of(2016, 1, 7);
        this.cq = new ControlQuality(this.warehouse, this.shop, this.trash, this.today);
        cq.sort(this.meat);
        double expectedPrice = this.meat.getPrice();
        double resultPrice = this.shop.getByName(this.meat.getName()).getPrice();
        assertThat(expectedPrice, is(resultPrice));
    }

    /**
     * Tests if percent of Life time is in range 75..100%  then food instance stores in shop.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty() {
        this.today = LocalDate.of(2016, 1, 25);
        this.cq = new ControlQuality(this.warehouse, this.shop, this.trash, this.today);
        cq.sort(this.milk);
        IFood expectedFood = this.milk;
        IFood resultFood = this.shop.getByName(this.milk.getName());
        assertThat(expectedFood, is(resultFood));
        assertThat(null, is(this.warehouse.getByName(this.meat.getName())));
        assertThat(null, is(this.trash.getByName(this.meat.getName())));
    }

    /**
     * Tests if percent of Life time is in range 75..1005% then food price discounted.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenSeventyFiveThenPriceIsDiscounted() {
        double milkStartPrice = this.milk.getPrice();
        this.today = LocalDate.of(2016, 1, 25);
        this.cq = new ControlQuality(this.warehouse, this.shop, this.trash, this.today);
        cq.sort(this.milk);
        double expectedPrice = milkStartPrice - milkStartPrice * this.milk.getDiscount() / 100;
        double resultPrice = this.shop.getByName(this.milk.getName()).getPrice();
        assertThat(expectedPrice, is(resultPrice));
    }

    /**
     * Tests if percent of Life time is more than 100% then food instance stores in trash.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenOneHundredThenFoodIsInTrashAndWarehouseAndShopAreEmpty() {
        this.today = LocalDate.of(2017, 1, 25);
        this.cq = new ControlQuality(this.warehouse, this.shop, this.trash, this.today);
        cq.sort(this.fish);
        IFood expectedFood = this.fish;
        IFood resultFood = this.trash.getByName(this.fish.getName());
        assertThat(expectedFood, is(resultFood));
        assertThat(null, is(this.warehouse.getByName(this.fish.getName())));
        assertThat(null, is(this.shop.getByName(this.fish.getName())));
    }
}