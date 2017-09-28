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
 * Test class for resort method of ControlQuality class.
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
     * Initiates initial values for tests.
     */
    @Before
    public void init() {
        this.warehouse.add(this.spoiledFish);
        this.trash.add(this.fish);
        this.shop.add(this.milk);
        this.warehouse.add(this.meat);
        int count = 0;
        this.stores[count++] = this.warehouse;
        this.stores[count++] = this.shop;
        this.stores[count++] = this.trash;

        this.cq = new ControlQuality();
        this.cq.resort(this.stores);
    }

    /**
     * Tests if spoiledFish is in Trash after resort.
     */
    @Test
    public void whenResortThenSpoiledFishIsInTrash() {
        IFood expectedFood = this.spoiledFish;
        IFood resultFood = this.trash.getByName(this.spoiledFish.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if spoiledFish is not in Warehouse after resort.
     */
    @Test
    public void whenResortThenSpoiledFishIsNotInWarehouse() {
        IFood expectedFood = null;
        IFood resultFood = this.warehouse.getByName(this.spoiledFish.getName());
        assertThat(expectedFood, is(resultFood));
    }
    /**
     * Tests if spoiledFish is not in Shop after resort.
     */
    @Test
    public void whenResortThenSpoiledFishIsNotInShop() {
        IFood expectedFood = null;
        IFood resultFood = this.shop.getByName(this.spoiledFish.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if Fish is in Warehouse after resort.
     */
    @Test
    public void whenResortThenFishIsInWarehouse() {
        IFood expectedFood = this.fish;
        IFood resultFood = this.warehouse.getByName(this.fish.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if Fish is not in Trash after resort.
     */
    @Test
    public void whenResortThendFishIsNotInTrash() {
        IFood expectedFood = null;
        IFood resultFood = this.trash.getByName(this.fish.getName());
        assertThat(expectedFood, is(resultFood));
    }
    /**
     * Tests if Fish is not in Shop after resort.
     */
    @Test
    public void whenResortThenFishIsNotInShop() {
        IFood expectedFood = null;
        IFood resultFood = this.shop.getByName(this.fish.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if Milk is in Shop after resort.
     */
    @Test
    public void whenResortThenMilkIsInShop() {
        IFood expectedFood = this.milk;
        IFood resultFood = this.shop.getByName(this.milk.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if Milk is not in Trash after resort.
     */
    @Test
    public void whenResortThenMilkIsNotInTrash() {
        IFood expectedFood = null;
        IFood resultFood = this.trash.getByName(this.milk.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if Milk is not in Warehouse after resort.
     */
    @Test
    public void whenResortThenMilkIsNotInWarehouse() {
        IFood expectedFood = null;
        IFood resultFood = this.warehouse.getByName(this.milk.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if Meat is in Shop after resort.
     */
    @Test
    public void whenResortThenMeatIsInShop() {
        IFood expectedFood = this.meat;
        IFood resultFood = this.shop.getByName(this.meat.getName());
        assertThat(expectedFood, is(resultFood));
    }

    /**
     * Tests if Meat is not in Trash after resort.
     */
    @Test
    public void whenResortThenMeatIsNotInTrash() {
        IFood expectedFood = null;
        IFood resultFood = this.trash.getByName(this.meat.getName());
        assertThat(expectedFood, is(resultFood));
    }
    /**
     * Tests if Meat is not in Warehouse after resort.
     */
    @Test
    public void whenResortThenMeatIsNotInWarehouse() {
        IFood expectedFood = null;
        IFood resultFood = this.warehouse.getByName(this.meat.getName());
        assertThat(expectedFood, is(resultFood));
    }

}