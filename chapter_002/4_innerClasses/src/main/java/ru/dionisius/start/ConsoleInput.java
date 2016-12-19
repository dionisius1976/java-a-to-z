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
}