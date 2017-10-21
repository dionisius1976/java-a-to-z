package ru.dionisius.controls;

import java.util.Scanner;

/**
 * Class ConsoleInput.
 * Gets string 'question', prints it and
 * uses scanner for reading console input
 * string 'answer'.
 */
public class ConsoleInput implements IInput {
	/**
	 * Scanner instance for reading from console.
	 */
	private Scanner scanner = new Scanner(System.in);
	/**
	 * Gets string 'question', prints it and
	 * Uses scanner for reading console input
	 * string 'answer'.
	 * @param question string 'quastion'.
	 * @return string 'answer'.
	 */
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}

	/**
	 * Validate user's input.
	 * Gets string 'question', validate if user's answer
	 * belong to validate answers range and returns string 'answer'.
	 * If inputted by user ru.dionisius.data is not valid throws MenuOutException.
	 * @param question question.
	 * @param range validate answers range.
	 * @return string 'answer'.
	 */
	public int ask(String question, int range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = key >= 0 && key <= range;
		if (exist) {
			return key;
		} else {
			throw new OutOfRangeException("Выход из диапазона возможных значений!");
		}
	}
}