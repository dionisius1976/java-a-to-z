package ru.dionisius.input;

/**
 * Created by Dionisius on 02.12.2016.
 */
public class MenuOutException extends RuntimeException {

    /**
     * Constructor.
     * @param msg message about this exception.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
