package ru.dionisius.controllers;
/**
 * Class starts the program.
 */
public class StartUI {
	/**
	 * Type of input.
	 */
	private Input input;
	/**
	 * Traxker instance.
	 */
	private Tracker tracker;
	/**
	 * Default constructor.
	 * @param input Type of input.
	 * @param tracker Type of tracker.
	 */
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	/**
	 * Initiate controllers of the program.
	 * @param menu Type of tracker.
	 */
	public void init(MenuTracker menu) {
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Выберете действие: "));
			menu.select(key);
		} while (!"y".equals(this.input.ask("Выход? (y/n)")));
	}
	/**
	 * Starts the program in operating system.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		ConsoleInput input = new ConsoleInput();
		MenuTracker menu = new MenuTracker(input, tracker);
		menu.fillActions();
		new StartUI(input, tracker).init(menu);
	}
}