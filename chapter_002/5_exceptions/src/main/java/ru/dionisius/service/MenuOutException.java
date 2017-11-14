package ru.dionisius.service;
/**
 * Exception that throws if inputted by user menu option is out of valid range.
 */
public class MenuOutException extends RuntimeException {
	/**
	 * Default constructor.
	 * @param msg message of this exception.
	 */
	public MenuOutException(String msg) {
		super(msg);
	}
}