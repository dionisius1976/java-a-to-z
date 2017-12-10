package ru.dionisius.service.input;

import ru.dionisius.service.exceptions.MenuOutException;

import java.util.Scanner;

/**
 * Class ConsoleInput.
 * Gets string 'question', prints it and
 * uses scanner for reading console input
 * string 'answer'.
 */
public class ConsoleInput implements Input {
	/**
	 * Scanner instanse for reading from console.
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
	 * If inputted by user ru.dionisius.ru.dionisius.data is not valid throws MenuOutException.
	 * @param question question.
	 * @param range validate answers range.
	 * @return string 'answer'.
	 */
	public int ask(String question, int[] range) {
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for (int value: range) {
			if (value == key) {
				exist = true;
				break;
			}
		}
		if (exist) {
			return key;
		} else {
			throw new MenuOutException("Выход из диапазона возможных значений!");
		}

	}
}