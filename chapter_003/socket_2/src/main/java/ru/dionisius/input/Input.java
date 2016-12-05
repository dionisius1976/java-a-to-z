package ru.dionisius.input;

/**
 * Created by Dionisius on 02.12.2016.
 */
public interface Input {
    String ask(String quastion);
    int ask(String quastion, int[] range);
}
