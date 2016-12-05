package ru.dionisius.trackers;

import ru.dionisius.input.Input;

import java.io.File;

/**
 * Created by Dionisius on 05.12.2016.
 */
public abstract class AClientTracker extends ATracker {

    /**
     *
     */
    protected Input input;

    /**
     * @param input
     * @param properties
     */
    public AClientTracker(Input input, File properties) {
        super(properties);
        this.input = input;
    }
}
