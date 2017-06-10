package ru.dionisius.controllers;

import ru.dionisius.models.Comment;
import ru.dionisius.models.Item;

import java.util.Date;
import java.util.Random;

/**
 * Data base fot models.
 * It has methods for adding, deleting,
 * finding by id, updates and get all models.
 */
public class Tracker {
	/**
	 * Available number of models for this tracker.
	 */
	static final int NUMBER_OF_ITEMS = 10;
	/**
	 * Array of models.
	 */
	private Item[] items = new Item[NUMBER_OF_ITEMS];
	/**
	 * Instance of Random class for generating random values.
	 */
	private static final Random RN = new Random();
	/**
	 * Generates random item's id.
	 * @return generated item's id.
	 */
	private long generateId() {
		return Long.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
	/**
	 * Adds specified item in the array.
	 * @param item specified item.
	 */
	public void add(Item item) {
		if (!isItemsFree()) {
			Item[] newItems = new Item[this.items.length * 2];
				System.arraycopy(items, 0, newItems, 0, this.items.length);
				this.items = newItems;
		}
		item.setId(this.generateId());
		for (int index = 0; index < this.items.length; index++) {
			if (this.items[index] == null) {
				this.items[index] = item;
				break;
			}
		}
	}
	/**
	 * Returns array of all models of this tracker.
	 * @return array of all models of this tracker.
	 */
	public Item[] getAll() {
		int getAllIndex = 0;
		Item[] result = new Item[this.items.length];
		for (int index = 0; index < this.items.length; index++) {
			if (this.items[index] == null) {
				continue;
			}
			result[getAllIndex] = this.items[index];
			getAllIndex++;
		}
		if (getAllIndex < result.length) {
			Item[] temp = new Item[getAllIndex];
			System.arraycopy(result, 0, temp, 0, getAllIndex);
			result = temp;
		}
		return result;
	}
	/**
	 * Deletes specified item.
	 * @param item specified item.
	 */
	public void delete(Item item) {
		for (int index = 0; index < this.items.length; index++) {
			if (this.items[index] == null) {
				continue;
			}
			if (this.items[index].equals(item)) {
				this.items[index] = null;
			}
		}
	}
	/**
	 * Finds item by its id.
	 * @param id specified id.
	 * @return founded item.
	 */
	public Item findById(long id) {
		Item result = null;
		for (int index = 0; index < this.items.length; index++) {
			if (this.items[index] == null) {
				continue;
			}
			if (this.items[index].getId() == id) {
				result = this.items[index];
			}
		}
		return result;
	}
	/**
	 * Updates (edits) specified item, identified by its id.
	 * @param id id of editing item.
	 * @param newName the new name of editing item.
	 * @param newDesc the new description of editing item.
	 */
	public void update(long id, String newName, String newDesc) {
		Item oldItem = this.findById(id);
		Date oldDate = oldItem.getCreate();
		Comment[] oldComments = oldItem.getComments();
		this.delete(oldItem);
		Item newItem = new Item(newName, newDesc);
		this.add(newItem);
		newItem.setDate(oldDate);
		newItem.setComments(oldComments);
		newItem.setId(id);
	}
	/**
	 * Verifies if the array of models is empty.
	 * @return true if it is empty and false if not.
	 */
	private boolean isItemsFree() {
		boolean result = false;
		for (int index = 0; index < this.items.length; index++) {
			if (this.items[index] == null) {
				result = true;
				break;
			}
		}
		return result;
	}
}