package ru.dionisius;

import ru.dionisius.Controllers.Controller;
import ru.dionisius.Models.Block;
import ru.dionisius.Models.Bomberman;
import ru.dionisius.Models.Field;
import ru.dionisius.Models.Monster;
import ru.dionisius.View.View;

/**
 * Created by Dionisius on 25.03.2017.
 * Runs the application Bomberman.
 */
public class Runner {

    /**
     * Initiates program running.
     */
    private static void init() {
        Field field = new Field(5, 8);
        field.setFigure(new Block(), 2, 2);
        field.setFigure(new Block(), 0, 4);
        field.setFigure(new Block(), 3, 0);
        field.setFigure(new Block(), 4, 7);
        Thread bomberman = new Thread(new Bomberman(field, 0, 0));
        Thread monster1 = new Thread(new Monster(field, 4, 4));
        Thread monster2 = new Thread(new Monster(field, 2, 3));
        Thread monster3 = new Thread(new Monster(field, 0, 3));
        Thread show = new Thread(new View(field));
        show.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bomberman.start();
        monster1.start();
        monster2.start();
        monster3.start();
        while (!Controller.youAreLoose) {
            try {
                show.join(200);
                bomberman.join(200);
                monster1.join(200);
                monster2.join(200);
                monster3.join(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            show.join();
            bomberman.join();
            monster1.join();
            monster2.join();
            monster3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Game is over.");
    }

    /**
     * Starts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        init();
    }
}
