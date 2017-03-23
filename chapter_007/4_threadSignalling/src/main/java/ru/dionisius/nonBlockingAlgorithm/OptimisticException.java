package ru.dionisius.nonBlockingAlgorithm;

/**
 * Created by Dionisius on 23.03.2017.
 */
public class OptimisticException extends RuntimeException {
    /**
     * Constructor.
     * @param msg message of this exception.
     */
    public OptimisticException(String msg) {
        super(msg);
    }
}
