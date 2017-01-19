package ru.dionisius.exceptions;

/**
 * Created by Dionisius on 19.01.2017.
 * This exception throwing in case
 * when there are unused keys in keymap.
 */
public class ExcessKeysException extends RuntimeException {
    /**
     * Default constructor.
     * @param message message of this exception.
     */
    public ExcessKeysException(String message) {
        super(message);
    }
}
