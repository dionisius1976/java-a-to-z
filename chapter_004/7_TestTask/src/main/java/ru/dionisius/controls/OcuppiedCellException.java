package ru.dionisius.controls;

/**
 * Created by Dionisius on 23.01.2017.
 */
public class OcuppiedCellException extends RuntimeException {
    /**
     * Constructor.
     * @param message specified message for this exception.
     */
    public OcuppiedCellException(String message) {
        super(message);
    }
}
