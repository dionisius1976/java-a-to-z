package ru.dionisius.start;

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
	 * Gets string 'question'.
	 * Prints it and returns by using scanner
	 * for reading console input
	 * string 'answer'.
	 * @param quastion question.
	 * @return string 'answer'.
	 */
	public String ask(String quastion) {
		System.out.print(quastion);
		return scanner.nextLine();
	}
}