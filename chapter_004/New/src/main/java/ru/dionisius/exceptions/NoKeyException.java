package ru.dionisius.exceptions;

/**
 * Created by Dionisius on 18.01.2017.
 * This exception throwing in case
 * when there is no key in keymap
 * matching to key in specified string.
 */
public class NoKeyException extends RuntimeException {
    /**
     * Default constructor.
     * @param message message of this exception.
     */
    public NoKeyException(String message) {
        super(message);
    }
}
