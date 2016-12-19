package ru.dionisius.exceptions;
/**
 * Exception for incorrect move of figure.
 */
public class IncorrectMoveException extends RuntimeException {
	/**
	 * Constructor.
	 * @param msg message of this exception.
	 */
	public IncorrectMoveException(String msg) {
		super(msg);
	}
}