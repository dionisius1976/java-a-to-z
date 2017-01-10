package ru.dionisius;

import org.junit.Before;
import org.junit.Test;
import ru.dionisius.controls.ExtControlQuality;
import ru.dionisius.stores.Reproduction;
import ru.dionisius.stores.Refrigerator;
import ru.dionisius.wares.Bones;
import ru.dionisius.wares.Bread;
import ru.dionisius.wares.Potato;
import ru.dionisius.wares.Carrot;
import ru.dionisius.wares.AVegetable;
import ru.dionisius.wares.AReproductFood;
import ru.dionisius.wares.IFood;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Dionisius on 21.12.2016.
 * Test class for ExtControlQuality class.
 */
public class ExtControlQualityTest extends ControlQualityTest {
    /**
     * Store of refragerators.
     */
    private final Refrigerator[] refragerators = new Refrigerator[10];
    /**
     * Store of reproductions.
     */
    private final Reproduction[] reproductions = new Reproduction[10];
    /**
     * Store of vegetables items.
     */
    private final AVegetable[] vegetables = new AVegetable[10];
    /**
     * Store of reproducing food items.
     */
    private final AReproductFood[] reproductFoods = new AReproductFood[10];
    /**
     * ControlQuality instance.
     */
    private ExtControlQuality extCq;
    /**
     * ControlQualityTest instance.
     */
    private ControlQualityTest cqTest;
    /**
     * Potato instance.
     */
    private final AVegetable potato = new Potato("Sineglazka",
            LocalDate.now().minusMonths(7),
            LocalDate.now().plusMonths(7),
            30,
            30);
    /**
     * Spoiled carrot instance.
     */
    private final AVegetable spoiledCarrot = new Carrot("Sibirskaja",
            LocalDate.now().minusMonths(15),
            LocalDate.now().minusMonths(1),
            40,
            50);
    /**
     * Carrot instance.
     */
    private final AVegetable carrot = new Carrot("Stolichnaja",
            LocalDate.now().minusMonths(1),
            LocalDate.now().plusMonths(15),
            40,
            50);
    /**
     * Bones instance.
     */
    private final AReproductFood bones = new Bones("CowBones",
            LocalDate.now().minusDays(10),
            LocalDate.now().minusDays(1),
            90,
            70,
            true);
    /**
     * Bread instance.
     */
    private final AReproductFood bread = new Bread("Borodinskiy",
            LocalDate.now().minusDays(10),
            LocalDate.now().minusDays(1),
            45,
            50,
            false);
    /**
     * Initiates initial values for tests.
     */
    @Before
    public void init() {
        int count = 0;
        this.refragerators[count] = new Refrigerator();
        this.reproductions[count] = new Reproduction();
        this.reproductFoods[count++] = this.bones;
        this.reproductFoods[count++] = this.bread;
        count = 0;
        this.vegetables[count++] = this.carrot;
        this.vegetables[count++] = this.potato;
        this.vegetables[count++] = this.spoiledCarrot;
        count = 0;
        super.stores[count++] = super.warehouse;
        super.stores[count++] = super.shop;
        super.stores[count++] = super.trash;
        count = 0;
        super.foods[count++] = super.fish;
        super.foods[count++] = super.meat;
        super.foods[count++] = super.milk;
        super.foods[count++] = super.spoiledFish;

        this.extCq = new ExtControlQuality();
        this.extCq.sort(super.stores, super.foods);
        this.extCq.sort(this.refragerators, this.vegetables);
        this.extCq.sort(this.reproductions, this.reproductFoods);
    }
    /**
     * Tests if percent of Life time is less than 25% then food instance stores in Warehouse.
     */
    @Test
    public void whenPercentOfLifeTimeIsLessThenTwentyFiveThenFoodIsInWarehouseAndShopAndTrashAreEmpty() {
        super.whenPercentOfLifeTimeIsLessThenTwentyFiveThenFoodIsInWarehouseAndShopAndTrashAreEmpty();
    }
    /**
     * Tests if percent of Life time is less than 25% then food price is not changed.
     */
    @Test
    public void whenPercentOfLifeTimeIsLessThenTwentyFiveThenPriceIsTheSame() {
        super.whenPercentOfLifeTimeIsLessThenTwentyFiveThenPriceIsTheSame();
    }

    /**
     * Tests if percent of Life time is in range 25..75% then food instance stores in shop.
     */
    @Test
    public void whenPersentOfLifeTimeIsInRangeTwentyFiveSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty() {
        super.whenPersentOfLifeTimeIsInRangeTwentyFiveSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty();
    }

    /**
     * Tests if percent of Life time is in range 25..75% then food price is not changed.
     */
    @Test
    public void whenPercentOfLifeTimeIsInRangeTwentyFiveSeventyFiveThenPriceIsTheSame() {
        super.whenPercentOfLifeTimeIsInRangeTwentyFiveSeventyFiveThenPriceIsTheSame();
    }

    /**
     * Tests if percent of Life time is in range 75..100%  then food instance stores in shop.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty() {
        super.whenPercentOfLifeTimeIsMoreThenSeventyFiveThenFoodIsInShopAndWarehouseAndTrashAreEmpty();
    }

    /**
     * Tests if percent of Life time is in range 75..1005% then food price discounted.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenSeventyFiveThenPriceIsDiscounted() {
        super.whenPercentOfLifeTimeIsMoreThenSeventyFiveThenPriceIsDiscounted();
    }
    /**
     * Tests if percent of Life time is more than 100% then food instance stores in trash.
     */
    @Test
    public void whenPercentOfLifeTimeIsMoreThenOneHundredThenFoodIsInTrashAndWarehouseAndShopAreEmpty() {
        super.whenPercentOfLifeTimeIsMoreThenOneHundredThenFoodIsInTrashAndWarehouseAndShopAreEmpty();
    }
    /**
     * Tests if only vegetables for warehouse are in refrigerator.
     */
    @Test
    public void whenVegetablesAreSortedThenOnlyVegetablesForWarehouseAreInRefrigerator() {
        IFood expectedVegetable = this.carrot;
        IFood resultVegetable = this.refragerators[0].getByName(this.carrot.getName());
        assertThat(resultVegetable, is(expectedVegetable));
    }
    /**
     * Tests if vegetables for trash are not in refrigerator.
     */
    @Test
    public void whenVegetablesAreSortedThenSpoiledVegetablesAreNotInRefrigerator() {
        IFood expectedVegetable = null;
        IFood resultVegetable = this.refragerators[0].getByName(this.spoiledCarrot.getName());
        assertThat(resultVegetable, is(expectedVegetable));
    }
    /**
     * Tests if vegetables for shop are not in refrigerator.
     */
    @Test
    public void whenVegetablesAreSortedThenVegetablesForShopAreNotInRefrigerator() {
        IFood expectedVegetable = null;
        IFood resultVegetable = this.refragerators[0].getByName(this.potato.getName());
        assertThat(resultVegetable, is(expectedVegetable));
    }
    /**
     * Tests if reproduct foods that can be reproduced are in Reproduction.
     */
    @Test
    public void whenReproductFoodsAreSortedThenReproductFoodsThatCanBeReproductAreInReproduction() {
        IFood expectedReproductFood = this.bones;
        IFood resultReproductFood = this.reproductions[0].getByName(this.bones.getName());
        assertThat(resultReproductFood, is(expectedReproductFood));
    }
    /**
     * Tests if reproduct foods that not can be reproduced are in Reproduction.
     */
    @Test
    public void whenReproductFoodsAreSortedThenReproductFoodsThatCanNotBeReproductAreNotInReproduction() {
        IFood expectedReproductFood = null;
        IFood resultReproductFood = this.reproductions[0].getByName(this.bread.getName());
        assertThat(resultReproductFood, is(expectedReproductFood));
    }
}