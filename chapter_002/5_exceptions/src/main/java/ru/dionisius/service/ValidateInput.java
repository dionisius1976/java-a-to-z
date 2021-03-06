package ru.dionisius.service;

/**
 * Validates user's inputted ru.dionisius.ru.dionisius.data.
 * If this ru.dionisius.ru.dionisius.data is invalid prints error message
 * and ask to input valid ru.dionisius.ru.dionisius.data.
 * If inputted by user menu option is out of valid range
 * throws MenuOutException.
 * If inputted by user menu option is not a number
 * throws NumberFormatException.
 */
public class ValidateInput extends ConsoleInput {
	/**
	 * * Validates user's inputted ru.dionisius.ru.dionisius.data.
	 * If this ru.dionisius.ru.dionisius.data is invalid prints error message
	 * and ask to input valid ru.dionisius.ru.dionisius.data.
	 * If inputted by user menu option is out of valid range
	 * throws MenuOutException.
	 * If inputted by user menu option is not a number
	 * throws NumberFormatException.
	 * @param quastion 'question'.
	 * @param range validate answers range.
	 * @return inputted by user menu option.
	 */
	public int ask(String quastion, int[] range) {
		boolean invalid = true;
		int value = -1;
		do {
			try {
				value = super.ask(quastion, range);
				invalid = false;
			} catch (MenuOutException moe) {
				System.out.println("Введите данные из заданного диапазона.");
			} catch (NumberFormatException nfe) {
				System.out.println("Введите корректные данные.");
			}
		} while (invalid);
		return value;
	}
}