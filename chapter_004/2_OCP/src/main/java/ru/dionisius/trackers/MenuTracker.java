package ru.dionisius.trackers;

import ru.dionisius.actions.BaseAction;
import ru.dionisius.actions.UserAction;
import ru.dionisius.inputs.Input;

/**
 * Selects specified action depending of chosen by user key and starts this action.
 */
public class MenuTracker {
	/**
	 * Result of previous operation.
	 */
	protected double result;
	/**
	 * Flag to check if the operation is the first or program has a previous operation result.
	 */
	protected boolean isFirst = true;
	/**
	 * Current operating system line separator.
	 */
	protected final String lineSep = System.lineSeparator();
	/**
	 * Specified type of input.
	 */
	protected final Input input;
	/**
	 * Number of available actions.
	 */
	static final int NUMBER_OF_ACTIONS = 4;
	/**
	 * Menu option 0.
	 */
	static final int MENU_OPTION_0 = 0;
	/**
	 * Menu option 1.
	 */
	static final int MENU_OPTION_1 = 1;
	/**
	 * Menu option 2.
	 */
	static final int MENU_OPTION_2 = 2;
	/**
	 * Menu option 3.
	 */
	static final int MENU_OPTION_3 = 3;

	/**
	 * Array of available actions.
	 */
	protected final UserAction[] actions = new UserAction[NUMBER_OF_ACTIONS];
	/**
	 * Default constructor.
	 * @param input type of input.
	 */
	public MenuTracker(final Input input) {
		this.input = input;
	}

	/**
	 * Getter for result of previous operation.
	 * @return result of previous operation.
	 */
	public double getResult() {
		return result;
	}

	/**
	 * Getter for the flag to check if the operation is the first.
	 * @return the flag to check if the operation is the first.
	 */
	public boolean getIsFirst() {
		return isFirst;
	}
	/**
	 * Fills action array by available actions.
	 */
	public void fillActions() {
		this.actions[MENU_OPTION_0] = this.new Addition("Сложить.");
		this.actions[MENU_OPTION_1] = this.new Subtraction("Вычесть.");
		this.actions[MENU_OPTION_2] = this.new Multiplication("Умножить.");
		this.actions[MENU_OPTION_3] = this.new Division("Разделить.");
	}
	/**
	 * Returns range of menu options.
	 * @return range of menu options.
	 */
	public int[] getRange() {
		int[] result = new int[this.actions.length];
		for (int index = 0; index < this.actions.length; index++) {
			result[index] = index;
		}
		return result;
	}
	/**
	 * Selects specified action depending of chosen by user key and starts this action.
	 * @param key chosen by user key.
	 */
	public void select(int key) {
		this.actions[key].execute(this.input);
		this.isFirst = false;
	}
	/**
	 * Shows menu with available actions.
	 */
	public void show() {
		for (UserAction action: this.actions) {
			if (action != null) {
				System.out.println(action.info());
			}
		}
	}
	/**
	 * Calculates addition of two specified double values.
	 */
	protected class Addition extends BaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		Addition(String name) {
			super(name);
		}
		/**
		 * Specified key for this action.
		 */
		static final int KEY = 0;
		/**
		 * Returns specified key for this class instance that using in menu of available actions.
		 * @return specified key for this class instance.
		 */
		public int key() {
			return KEY;
		}
		/**
		 * Calculates addition of two specified double values.
		 * @param input type of input.
		 */
		public void execute(Input input) {
			double firstValue = 0;
			if (MenuTracker.this.isFirst) {
				firstValue = Double.valueOf(input.ask("Введите значене первого слагаемого: "));
			} else {
				firstValue = MenuTracker.this.result;
			}
			double secondValue = Double.valueOf(input.ask("Введите значене второго слагаемого: "));
			MenuTracker.this.result = firstValue + secondValue;
			System.out.printf("Сумма чисел равна: %f%s", MenuTracker.this.result, MenuTracker.this.lineSep);
		}
	}
	/**
	 * Calculates subtraction of two double values.
	 */
	protected class Subtraction extends BaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		Subtraction(String name) {
			super(name);
		}
		/**
		 * Specified key for this action.
		 */
		static final int KEY = 1;
		/**
		 * Returns specified key for this class instance that using in menu of available actions.
		 * @return specified key for this class instance.
		 */
		public int key() {
			return KEY;
		}
		/**
		 * Calculates subtraction of two double values.
		 * @param input type of input.
		 */
		public void execute(Input input) {
			double firstValue = 0;
			if (MenuTracker.this.isFirst) {
				firstValue = Double.valueOf(input.ask("Введите значение уменьшаемого: "));
			} else {
				firstValue = MenuTracker.this.result;
			}
			double secondValue = Double.valueOf(input.ask("Введите значение вычитаемого: "));
			MenuTracker.this.result = firstValue - secondValue;
			System.out.printf("Разность чисел равна: %f%s", MenuTracker.this.result, MenuTracker.this.lineSep);
		}
	}
	/**
	 * Calculates multiplication of two specified double values.
	 */
	protected class Multiplication extends BaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		Multiplication(String name) {
			super(name);
		}
		/**
		 * Specified key for this action.
		 */
		static final int KEY = 2;
		/**
		 * Returns specified key for this class instance that using in menu of available actions.
		 * @return specified key for this class instance.
		 */
		public int key() {
			return KEY;
		}
		/**
		 * Calculates multiplication of two specified double values.
		 * @param input type of input.
		 */
		public void execute(Input input) {
			double firstValue = 0;
			if (MenuTracker.this.isFirst) {
				firstValue = Double.valueOf(input.ask("Введите значение первого множителя: "));
			} else {
				firstValue = MenuTracker.this.result;
			}
			double secondValue = Double.valueOf(input.ask("Введите значение второго множителя: "));
			MenuTracker.this.result = firstValue * secondValue;
			System.out.printf("Произведение чисел равно: %f%s", MenuTracker.this.result, MenuTracker.this.lineSep);
		}
	}
	/**
	 * Calculates division of two specified double values.
	 */
	protected class Division extends BaseAction implements UserAction {
		/**
		 * Default constructor.
		 * @param name specified name of this class execute action.
		 */
		Division(String name) {
			super(name);
		}
		/**
		 * Specified key for this action.
		 */
		static final int KEY = 3;
		/**
		 * Returns specified key for this class instance that using in menu of available actions.
		 * @return specified key for this class instance.
		 */
		public int key() {
			return KEY;
		}
		/**
		 * Calculates division of two specified double values.
		 * @param input type of input.
		 */
		public void execute(Input input) {
			double firstValue = 0;
			if (MenuTracker.this.isFirst) {
				firstValue = Double.valueOf(input.ask("Введите значение делимого: "));
			} else {
				firstValue = MenuTracker.this.result;
			}
			double secondValue = Double.valueOf(input.ask("Введите значение делителя: "));
			MenuTracker.this.result = firstValue / secondValue;
			System.out.printf("Частное равно: %f%s", MenuTracker.this.result, MenuTracker.this.lineSep);
		}
	}
}