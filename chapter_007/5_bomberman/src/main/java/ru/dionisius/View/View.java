package ru.dionisius.View;

import ru.dionisius.Controllers.Controller;
import ru.dionisius.Models.Cell;
import ru.dionisius.Models.Field;
import ru.dionisius.Models.IFigure;

/**
 * Created by Dionisius on 25.03.2017.
 */
public class View implements Runnable {

    private volatile Field field;

    public View(Field field) {
        this.field = field;
    }

    private void show() {

        Cell[][] figures = this.field.getField();
        for (int i = 0; i < figures.length; i++) {
            for (int j = 0; j < figures[0].length; j++) {
                if (figures[i][j].getFigure() == null) {
                    System.out.print("[ ]");
                    continue;
                }
                if (figures[i][j].getFigure().getName().equals("B")) {
                    System.out.print("[X]");
                    continue;
                }
                if (figures[i][j].getFigure().getName().equals("BOMB")) {
                    System.out.print("[B]");
                    continue;
                }
                if (figures[i][j].getFigure().getName().equals("M")) {
                    System.out.print("[M]");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    @Override
    public void run() {
        while (!Controller.youAreLoose) {
            this.show();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
