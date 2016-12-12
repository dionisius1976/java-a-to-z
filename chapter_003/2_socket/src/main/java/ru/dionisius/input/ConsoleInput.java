package ru.dionisius.input;

import java.util.Scanner;

/**
 * Created by Dionisius on 02.12.2016.
 */
public class ConsoleInput implements Input {

    /**
     * Scanner for reding user's console input.
     */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String quastion) {
        System.out.print(quastion);
        return scanner.nextLine();
    }

    @Override
    public int ask(String quastion, int[] range) {
        int key = Integer.valueOf(this.ask(quastion));
        boolean exist = false;
        for (int value: range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Выход из диапазона возможных значений!");
        }
    }
}

