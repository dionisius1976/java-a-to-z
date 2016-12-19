package ru.dionisius.start;

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
		Input input = new StubInput(new String[] {"0", "First", "First desc", "n", "0", "Second",
				"Second desc", "n", "5", "n", "7", "First", "n", "7", "Third", "n", "8", "First desc",
				"n", "8", "Desc", "y"});
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(new ConsoleInput(), tracker);
		menu.fillActions();
		new StartUI(input, tracker).init(menu);
	}
}