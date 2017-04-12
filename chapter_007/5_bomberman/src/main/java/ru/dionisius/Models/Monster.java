package ru.dionisius.Models;

import ru.dionisius.Controllers.Controller;

import java.util.Random;

/**
 * Created by Dionisius on 24.03.2017.
 */
public class Monster implements IFigure, Runnable {
    /**
     * Sign for this instance.
     */
    private final static String name = "M";
    /**
     * Initial x coordinate.
     */
    private int coordinateX;
    /**
     * Initial y coordinate.
     */
    private int coordinateY;
    /**
     * Field instance.
     */
    private volatile Field field;
    /**
     * Constructor.
     * @param field specified field.
     * @param coordinateX initial x coordinate.
     * @param coordinateY initial y coordinate.
     */
    public Monster(final Field field, final int coordinateX, final int coordinateY) {
        this.field = field;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.field.setFigure(this,coordinateX, coordinateY);
    }
    /**
     * Moves this figure.
     */
    public void move() {
        IFigure destFigure = null;
        int newX;
        int newY;
        boolean move = false;
        while (!move) {
            newX = this.randomChange(this.coordinateX);
            newY = this.randomChange(this.coordinateY);
            if (this.isMoveAvailable(newX, newY)) {
                destFigure = this.field.getField()[newX][newY].getFigure();
                if (destFigure == null) {
//                    this.field.remove(this.coordinateX, this.coordinateY);
                    this.field.moveFigure(this, this.coordinateX, this.coordinateY, newX, newY);
                    this.coordinateX = newX;
                    this.coordinateY = newY;

//                    this.field.setFigure(this, newX, newY);
                    move = true;
                } else if (destFigure.getName().equals("B") ||
                        this.field.getField()[newX][newY].getFigure().getName().equals("M")) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (destFigure.getName().equals("BOMB")) {
                    Controller.youAreLoose = true;
                    break;
                }
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }
    /**
     * Checks if specified coordinates are valid.
     * @param coordinateX specified x coordinate.
     * @param coordinateY specified y coordinate.
     * @return true if specified coordinates are valid.
     */
    private boolean isMoveAvailable (final int coordinateX, final int coordinateY) {
        return coordinateX >= 0 && coordinateY >= 0 &&
                coordinateX < this.field.getField().length && coordinateY < this.field.getField()[0].length;
    }
    /**
     * Return random specified coordinate's shift.
     * @param coordinate specified coordinate.
     * @return random specified coordinate's shift.
     */
    private int randomChange(int coordinate) {
        int[] changed = {coordinate + 1, coordinate, coordinate - 1};
        return changed[new Random().nextInt(changed.length)];
    }

    @Override
    public void run() {
        while (!Controller.youAreLoose) {
            this.move();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
