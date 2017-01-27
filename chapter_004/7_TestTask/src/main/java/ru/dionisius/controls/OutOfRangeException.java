package ru.dionisius.controls;

/**
 * Created by Dionisius on 20.01.2017.
 */
public class OutOfRangeException  extends RuntimeException {
    /**
     * Constructor.
     * @param message specified message for this exception.
     */
    public OutOfRangeException(String message) {
        super(message);
    }
}
