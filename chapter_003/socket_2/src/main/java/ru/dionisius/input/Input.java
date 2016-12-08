package ru.dionisius.input;

/**
 * Created by Dionisius on 02.12.2016.
 */
public interface Input {

    /**
     * Gets and prints quastion and returns inputted answer.
     * @param question receiving question
     * @return user's answer
     */
    String ask(String question);

    /**
     * Asks the quastion and returns chosen by user menu's number of available action.
     * @param quastion  receiving question
     * @param range range of available
     * @return number
     */
    int ask(String quastion, int[] range);
}
