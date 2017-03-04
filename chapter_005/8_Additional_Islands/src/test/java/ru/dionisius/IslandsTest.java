package ru.dionisius;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dionisius on 04.03.2017.
 */
public class IslandsTest {
    private int[][] testOcean = {{1, 0, 1, 0, 1},
            {0, 0, 1, 1, 0},
            {0, 1, 1, 0, 1},
            {1, 0, 1, 1, 0}};
    private int[][] emptyOcean;
    private int[][] oneBigIsland;
    private Islands islands;

    @Before
    public void init() {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                this.emptyOcean[i][j] = 0;
            }
        }
    }

    @Test
    public void whenTestOceanThenIslandAreaIsExpectedValue() {
        int expectedValue = 7;
        this.islands = new Islands(this.testOcean);
//        int resultValue = new Islands(this.testOcean).init();

    }
}