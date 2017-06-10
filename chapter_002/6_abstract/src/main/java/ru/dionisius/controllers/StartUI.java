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
	 * Type of Tracker.
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
			int key = Integer.valueOf(input.ask("Выберете действие: ",  menu.getRange()));
			menu.select(key);
		} while (!"y".equals(this.input.ask("Выход? (y/n)")));
	}
	/**
	 * Starts the program in operating system.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
		Tracker tracker = new Tracker();
//		ValidateInput input = new ValidateInput();
		Input input = new StubInput(new String[]{"0", "name", "desc", "y"});
		MenuTracker menu = new MenuTracker(input, tracker);
		menu.fillActions();
//		new StartUI(input, tracker).init(menu);
		for (int i = 0; i < 20000; i++) {
			new StartUI(input, tracker).init(menu); // test for gc java.lang.OutOfMemoryError: GC overhead limit exceeded
			System.out.println("i = " + i);
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}