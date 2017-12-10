package ru.dionisius.input;

/**
 * Class checks user's input.
 * Inputed ru.dionisius.ru.dionisius.data must be a number from specified range of available actions
 * Created by Dionisius on 09.12.2016.
 */
public class ValidateInput extends ConsoleInput {

    @Override
    public int ask(String quastion, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(quastion, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Введите данные из заданного диапазона.");
            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректные данные.");
            }
        } while (invalid);
        return value;
    }
}

