package ru.dionisius;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dionisius on 04.03.2017.
 * This program counts the sum of all cells with value 1 (earth) that bordering
 * each other at horizontal or at vertical in specified matrix (ocean). Cells with value 0
 * are "water".
 */
public class Islands {
    /**
     * Specified ocean.
     */
    private final int[][] ocean;
    /**
     * Store of all visited cells of this ocean.
     */
    private final Set<Cell> visited = new HashSet<Cell>();
    /**
     * The largest area of all islands in this ocean.
     */
    private int biggestIslandArea = 0;

    /**
     * Constructor.
     * @param ocean specified ocean.
     */
    public Islands(int[][] ocean) {
        this.ocean = ocean;
    }

    /**
     * Starts the program.
     */
    public void init() {
        this.findBiggestIsland();
        this.showResult();
    }
    /**
     * Finds the largest area of all islands in this ocean.
     */
    private void findBiggestIsland() {
        for (int i = 0; i < this.ocean.length; i++) {
            for (int j = 0; j < this.ocean[0].length; j++) {
                if (this.ocean[i][j] == 1 && !this.visited.contains(new Cell(i, j))) {
                    int tempArea = this.calculateIslandArea(i, j);
                    if (this.biggestIslandArea < tempArea) {
                        this.biggestIslandArea = tempArea;
                    }
                }
            }
        }
    }

    /**
     * Calculates the area of specified island that starts at cell with coordinates X and Y.
     * @param i Y coordinate.
     * @param j X coordinate.
     * @return the area of specified island.
     */
    private int calculateIslandArea(int i, int j) {
        int thisIslandArea = 1;
        this.visited.add(new Cell(i, j));
        if (i + 1 <= this.ocean.length - 1 && this.ocean[i + 1][j] == 1 && !this.visited.contains(new Cell(i + 1, j))) {
            thisIslandArea += this.calculateIslandArea(i + 1, j);
        }
        if (i - 1 >= 0 && this.ocean[i - 1][j] == 1 && !this.visited.contains(new Cell(i - 1, j))) {
            thisIslandArea += this.calculateIslandArea(i - 1, j);
        }
        if (j - 1 >= 0 && this.ocean[i][j - 1] == 1 && !this.visited.contains(new Cell(i, j - 1))) {
            thisIslandArea += this.calculateIslandArea(i, j - 1);
        }
        if (j + 1 <= this.ocean[0].length - 1 && this.ocean[i][j + 1] == 1 && !this.visited.contains(new Cell(i, j + 1))) {
            thisIslandArea += this.calculateIslandArea(i, j + 1);
        }
        return thisIslandArea;
    }

    /**
     * Prints the area of the largest island.
     */
    private void showResult() {
        System.out.printf("%s%d", "The biggest island is ", this.biggestIslandArea);
    }
    /**
     * Cell class.
     */
    private class Cell {
        /**
         * X coordinate.
         */
        private final int x;
        /**
         * Y coordinate.
         */
        private final int y;

        /**
         * Constructor.
         * @param x X coordinate.
         * @param y Y coordinate.
         */
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (x != cell.x) return false;
            return y == cell.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public static void main(String[] args) {
        int[][] testOcean = {{0, 1, 1, 0, 1},
                            {0, 0, 1, 1, 1},
                            {0, 1, 1, 0, 1},
                            {1, 0, 1, 1, 0}};
        new Islands(testOcean).init();
    }
}
