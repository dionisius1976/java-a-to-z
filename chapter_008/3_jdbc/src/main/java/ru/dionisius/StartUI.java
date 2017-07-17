package ru.dionisius;

import ru.dionisius.controllers.input.Input;
import ru.dionisius.controllers.MenuTracker;
import ru.dionisius.controllers.Tracker;
import ru.dionisius.controllers.input.ValidateInput;

/**
 * Class starts the program.
 */
public class StartUI {
	/**
	 * Type of input.
	 */
	private final Input input;
	/**
	 * Type of Tracker.
	 */
	private final Tracker tracker;

	/**
	 * Constructor.
	 * @param input specified Input instance.
	 * @param tracker specified Tracker instance.
	 */
	public StartUI (final Input input, final Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * Initiate controllers of the program.
	 */
	public void init() {
		MenuTracker menu = new MenuTracker(input, tracker);
		this.tracker.connectToDb();
		menu.fillActions();
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Выберете действие: ",  menu.getRange()));
			menu.select(key);
		} while (!"y".equals(this.input.ask("Выход? (y/n)")));
		this.tracker.disconnectDb();
	}

	/**
	 * Starts the program in operating system.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
		Input input = new ValidateInput();
		Tracker sqlTracker = new Tracker();
		new StartUI(input, sqlTracker).init();
	}
}