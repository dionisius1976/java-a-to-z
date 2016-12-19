package ru.dionisius;

import ru.dionisius.inputs.Input;
import ru.dionisius.inputs.ValidateInput;
import ru.dionisius.trackers.MenuTracker;

/**
 *
 */
public class StartUI {
	/**
	 * Type of input.
	 */
	private Input input;
	private final String lineSep = System.lineSeparator();
	/**
	 * Default constructor.
	 * @param input Type of input.
	 */
	public StartUI(Input input) {
		this.input = input;
	}
	/**
	 * Initiate start of the program.
	 * @param menu Type of tracker.
	 */
	public void init(MenuTracker menu) {
		do {

			menu.show();
			if (!menu.getIsFirst()) {
				System.out.printf("Результат предыдущей операции: %f%s", menu.getResult(), this.lineSep);
			}
			int key = Integer.valueOf(input.ask("Выберете действие: ",  menu.getRange()));
			menu.select(key);
		} while (!"y".equals(this.input.ask("Выход? (y/n)")));
	}
	/**
	 * Starts the program in operating system.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
		ValidateInput input = new ValidateInput();
		MenuTracker menu = new MenuTracker(input);
		menu.fillActions();
		new StartUI(input).init(menu);
	}
}