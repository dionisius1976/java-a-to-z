package ru.dionisius.model;

import java.util.Date;
/**
 * Describes task for this program.
 */
public class Task extends Item {
	/**
	 * Specified date of creation of this item.
	 */
	private Date create;
	/**
	 * @param name specified name of this task.
	 * @param desc specified description of this task.
	 * @param create specified date of creation of this task.
	 */
	public Task(String name, String desc, Date create) {
		super(name, desc);
		this.create = new Date();
	}
	/**
	 * Calculates price.
	 * @return price value.
	 */
	public String calculatePrice() {
		return "100%";
	}
}
