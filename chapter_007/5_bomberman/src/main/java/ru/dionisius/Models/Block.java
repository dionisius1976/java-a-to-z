package ru.dionisius.Models;

/**
 * Created by Dionisius on 24.03.2017.
 */
public class Block implements IFigure {
    /**
     * Sign for this instance.
     */
    private final static String name = "B";

    @Override
    public String getName() {
        return name;
    }
}
