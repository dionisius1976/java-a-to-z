package ru.dionisius.controllers;

import org.junit.Before;
import org.junit.Test;
import ru.dionisius.model.Comment;
import ru.dionisius.model.Item;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * Testing class for class Tracker.
 * It tests all methods of class Tracker.
 */
public class TrackerTest {

	/**
	 * Item test instance.
	 */
	private Item item1;
	/**
	 * Item test instance.
	 */
	private Item item2;
	/**
	 * Item test instance.
	 */
	private Item item3;
	/**
	 * Item test instance.
	 */
	private Item item4;
	/**
	 * Item test instance.
	 */
	private Item item5;
	/**
	 * Item test instance.
	 */
	private Tracker tracker;
	/**
	 * Initialize variables.
	 */
	@Before
	public void setup() {
		item1 = new Item("Заявка1", "Description1");
		item2 = new Item("Заявка2", "Description2");
		item3 = new Item("Заявка3", "Description3");
		item4 = new Item("Заявка4", "Description4");
		item5 = new Item("Заявка5", "Description5");
		tracker = new Tracker();
	}
	/**
	 * Tests if model was added.
	 */
	@Test
	public void whenAddThenExpectedArray() {
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);
		tracker.add(item5);
		Item[] expectedArray = {item1, item2, item3, item4, item5};
		assertArrayEquals(expectedArray, tracker.getAll());
	}
	/**
	 * Tests if method get works.
	 */
	@Test
	public void whenGetAllThenExpectedArray() {
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);
		tracker.add(item5);
		Item[] expectedArray = {item1, item2, item3, item4, item5};
		assertArrayEquals(expectedArray, tracker.getAll());
	}
	/**
	 * Tests if method getById works.
	 */
	@Test
	public void whenFindByIdThenExpectedItem() {
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);
		tracker.add(item5);
	long expectedId = item2.getId();
	long resultId = tracker.findById(item2.getId()).getId();
	assertEquals(expectedId, resultId);
	}
	/**
	 * Tests if method delete works.
	 */
	@Test
	public void whenDeleteThenExpectedArray() {
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);
		tracker.add(item5);
		tracker.delete(item2);
		Item[] expectedArray = {item1, item3, item4, item5};
		assertArrayEquals(expectedArray, tracker.getAll());
	}
	/**
	 * Tests if method update works.
	 */
	@Test
	public void whenUpdateThenExpectedItem() {
		tracker.add(item1);
		tracker.add(item2);
		tracker.add(item3);
		tracker.add(item4);
		tracker.add(item5);
		long item3Id = item3.getId();
		String newName = "Заявка33";
		String newDesc = "Описание33";
		Date oldDate = item3.getCreate();
		Comment[] oldComments = item3.getComments();
		tracker.update(item3Id, newName, newDesc);
		String expectedName = newName;
		String expectedDesc = newDesc;
		assertEquals(expectedName, tracker.findById(item3Id).getName());
		assertEquals(expectedDesc, tracker.findById(item3Id).getDesc());
		assertEquals(oldDate, tracker.findById(item3Id).getCreate());
		//assertArrayEquals(oldComments, this.tracker.findById(item3Id).getComments());
	}
}