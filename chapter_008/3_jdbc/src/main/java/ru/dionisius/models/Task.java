package ru.dionisius.models;

import java.util.Date;

/**
 * Describes task for this program.
 */
public class Task extends Item {
	/**
	 * Specified name of this task.
	 */
	private  String name;
	/**
	 * Specified description of this task.
	 */
	private  String desc;
	/**
	 * Specified date of creation of this task.
	 */
	private Date create;
	/**
	 * @param name specified name of this task.
	 * @param desc specified description of this task.
	 * @param create specified date of creation of this task.
	 */
	public Task(String name, String desc, Date create) {
		this.name = name;
		this.desc = desc;
		this.create = create;
	}
	/**
	 * Calculates price.
	 * @return price value.
	 */
	public String calculatePrice() {
		return "100%";
	}
}
