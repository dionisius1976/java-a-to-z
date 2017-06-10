package ru.dionisius.controllers.action;

import ru.dionisius.controllers.Tracker;
import ru.dionisius.controllers.input.Input;

/**
 * Interface for all classes providing tracker's actions.
 */
public interface UserAction {
	/**
	 * Returns specified key for this class instance that using in menu of available actions.
	 * @return specified key for this class instance.
	 */
	int key();
	/**
	 * Executes main method for this class instance.
	 * @param input type of input.
	 * @param tracker type of tracker.
	 */
	void execute(Input input, Tracker tracker);
	/**
	 * Returns info about this class instance that using in menu of available actions.
	 * @return info about this class instance.
	 */
	String info();
}