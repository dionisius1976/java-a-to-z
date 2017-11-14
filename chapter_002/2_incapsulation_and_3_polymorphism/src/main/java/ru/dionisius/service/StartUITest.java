package ru.dionisius.service;

/**
 * Testing class for class StartUI.
 * Emulates user's console input.
 */
public class StartUITest {
	/**
	 * Starts user's console input emulator.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
		Input input = new StubInput(new String[] {"1", "First", "First desc",
												  "1", "Second", "Second desc",
												  "6",
												  "8", "First",
												  "8", "Third",
												  "9", "First desc",
												  "9", "Desc",
												  "10"});
		new StartUI(input).init();
	}
}