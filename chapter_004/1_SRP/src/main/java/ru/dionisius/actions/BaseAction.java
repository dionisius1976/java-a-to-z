package ru.dionisius.actions;

import ru.dionisius.inputs.Input;

/**
 * Abstract class for all classes providing tracker's actions.
 */
public abstract class BaseAction implements UserAction {

	/**
	 * The name of this action.
	 */
	private String name;

	/**
	 * Default constructor.
	 * @param name specified name of this action.
	 */
	public BaseAction(String name) {
		this.name = name;
	}
	/**
	 * Returns specified key for this class instance that using in menu of available actions.
	 * @return specified key for this class instance.
	 */
	public abstract int key();
	/**
	 * Executes main method for this class instance.
	 * @param input type of input.
	 */
	public abstract void execute(Input input);
	/**
	 * Returns info about this class instance that using in menu of available actions.
	 * @return info about this class instance.
	 */
	public String info() {
		return String.format("%s. %s", this.key(), this.name);
	}

}