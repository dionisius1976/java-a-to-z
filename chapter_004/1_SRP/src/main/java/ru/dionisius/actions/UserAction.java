package ru.dionisius.actions;

import ru.dionisius.inputs.Input;

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
	 */
	void execute(Input input);
	/**
	 * Returns info about this class instance that using in menu of available actions.
	 * @return info about this class instance.
	 */
	String info();
}