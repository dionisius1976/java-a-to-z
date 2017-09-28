package ru.dionisius;

import org.junit.Before;
import org.junit.Test;
import ru.dionisius.controls.ControlQuality;
import ru.dionisius.model.IStore;
import ru.dionisius.model.Shop;
import ru.dionisius.model.Trash;
import ru.dionisius.model.Warehouse;
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
     * Store of model.
     */
    protected final IStore[] stores = new IStore[10];
    /**
     * Store of food model.
     */
    protected final IFood[] foods = new IFood[10];
    /**
     * Warehouse instance.
     */
    protected final Warehouse warehouse = new Warehouse();
    /**
     * Shop instance.
     */
    protected final Shop shop = new Shop();
    /**
     * Trash instance.
     */
    protected final Trash trash = new Trash();
    /**
     * ControlQuality instance.
     */
    private ControlQuality cq;
    /**
     * Fish instance.
     */
    protected final IFood fish = new Fish("Beluga",
                                        LocalDate.now().minusMonths(2),
                                        LocalDate.now().plusMonths(10),
                                        3200,
                                        30,
                                        false,
                                        true);
    /**
     * Meat instance.
     */
    protected final IFood meat = new Meat("Beef",
                                        LocalDate.now().minusDays(7),
                                        LocalDate.now().plusDays(7),
                                        550,
                                        35,
                                        false,
                                        1);

    /**
     * Milk instance.
     */
    protected final IFood milk = new Milk("Prostokvashino",
                                        LocalDate.now().minusDays(20),
                                        LocalDate.now().plusDays(2),
                                        50,
                                        25,
                                        3.5);
    /**
     * Fish spoiled instance.
     */
    protected final IFood spoiledFish = new Fish("Herring",
                                        LocalDate.now().minusMonths(2),
                                        LocalDate.now().minusMonths(1),
                                        200,
                                        30,
                                        true,
                                        false);
    /**
     * Initial price of this fish without discount.
     */
    protected final double fishInitialPrice = this.fish.getPrice();
    /**
     * Initial price of this meat without discount.
     */
    protected final double meatInitialPrice = this.meat.getPrice();
    /**
     * Initial price of this milk without discount.
     */
    protected final double milkInitialPrice = this.milk.getPrice();

    /**
     * Initiates initial values for tests.
     */
    @Before
    public void init() {
        int count = 0;
        this.stores[count++] = this.warehouse;
        this.stores[count++] = this.shop;
        this.stores[count++] = this.trash;
        count = 0;
        this.foods[count++] = this.fish;
        this.foods[count++] = this.meat;
        this.foods[count++] = this.milk;
        this.foods[count++] = this.spoiledFish;

        this.cq = new ControlQuality();
        this.cq.sort(this.stores, this.foods);
    }

    /**
     * Tests if percent of Life time is less than 25% then food instance model in Warehouse.
     */
    @Test
    public void whenPercentOfLifeTimeIsLessThenTwentyFiveThenFoodIsInWarehouseAndShopAndTrashAreEmpty() {
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
        double expectedPrice = this.fishInitialPrice;
        double resultPrice = this.warehouse.getByName(this.fish.getName()).getPrice();
        assertThat(expectedPrice, is(resultPrice));
    }

    /**
     * Tests if percent of Life time is in range 25..75% then food instance model in shop.
     */
    @Test
    public void whenPersentOfLifeTimeIsInRangeTwentyFiveSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty() {
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
        double expectedPrice = this.meatInitialPrice;
        double resultPrice = this.shop.getByName(this.meat.getName()).getPrice();
        assertThat(expectedPrice, is(resultPrice));
    }

    /**
     * Tests if percent of Life time is in range 75..100%  then food instance model in shop.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty() {
        IFood expectedFood = this.milk;
        IFood resultFood = this.shop.getByName(this.milk.getName());
        assertThat(expectedFood, is(resultFood));
        assertThat(null, is(this.warehouse.getByName(this.milk.getName())));
        assertThat(null, is(this.trash.getByName(this.milk.getName())));
    }

    /**
     * Tests if percent of Life time is in range 75..1005% then food price discounted.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenSeventyFiveThenPriceIsDiscounted() {
        double expectedPrice = this.milkInitialPrice - this.milkInitialPrice * this.milk.getDiscount() / 100;
        double resultPrice = this.shop.getByName(this.milk.getName()).getPrice();
        assertThat(expectedPrice, is(resultPrice));
    }
    /**
     * Tests if percent of Life time is more than 100% then food instance model in trash.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenOneHundredThenFoodIsInTrashAndWarehouseAndShopAreEmpty() {
        IFood expectedFood = this.spoiledFish;
        IFood resultFood = this.trash.getByName(this.spoiledFish.getName());
        assertThat(expectedFood, is(resultFood));
        assertThat(null, is(this.warehouse.getByName(this.spoiledFish.getName())));
        assertThat(null, is(this.shop.getByName(this.spoiledFish.getName())));
    }
}