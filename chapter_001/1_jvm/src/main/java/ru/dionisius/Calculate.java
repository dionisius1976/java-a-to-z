package ru.dionisius;

/**
 * Class calculator.
 * Prints total string that consists of three inputted strings.
 */
public class Calculate {

	/**
	 * Returns total string that consists of three inputted strings.
	 * @param value inputted string
	 * @return total string
	 */
	public String echo(String value) {
		return String.format("%s %s %s", value, value, value);
	}
}