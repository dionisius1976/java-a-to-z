package ru.dionisius.start;

import org.junit.Test;
import ru.dionisius.models.Comment;
import ru.dionisius.models.Item;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * Testing class for class Tracker.
 * It tests all methods of class Tracker.
 */
public class TrackerTest {
	/**
	 * Specified first item for testing.
	 */
	private Item item1 = new Item("Заявка1", "Description1");
	/**
	 * Specified second item for testing.
	 */
	private Item item2 = new Item("Заявка2", "Description2");
	/**
	 * Specified third item for testing.
	 */
	private Item item3 = new Item("Заявка3", "Description3");
	/**
	 * Specified fourth item for testing.
	 */
	private Item item4 = new Item("Заявка4", "Description4");
	/**
	 * Specified fifth item for testing.
	 */
	private Item item5 = new Item("Заявка5", "Description5");
	/**
	 * New name for item for testing.
	 */
	static final String NEW_NAME = "Заявка33";
	/**
	 * New description for item for testing.
	 */
	static final String NEW_DESC = "Описание33";
	/**
	 * Tracker instance.
	 */
	private Tracker tracker = new Tracker();
	/**
	 * Array of specified models.
	 */
	private Item[] items = {item1, item2, item3, item4, item5};
	/**
	 * Expected array of models.
	 */
	private final Item[] expectedArray = {item1, item2, item3, item4, item5};
	/**
	 * Result array of specified tracker action.
	 */
	private Item[] resultArray;
	/**
	 * Adds models in tracker.
	 */
	private void init() {
		for (Item item: items) {
			this.tracker.add(item);
		}
	}
	/**
	 * Tests getAll method of the tracker.
	 */
	@Test
	public void whenGetAllThenExpectedArray() {
		this.init();
		this.resultArray = tracker.getAll();
		assertArrayEquals(this.expectedArray, this.resultArray);
	}
	/**
	 * Tests finding item by its id.
	 */
	@Test
	public void whenFindByIdThenExpectedItem() {
		this.init();
		long expectedId = item2.getId();
		long resultId = tracker.findById(item2.getId()).getId();
		assertEquals(expectedId, resultId);
	}
	/**
	 * Tests deleting item in tracker.
	 */
	@Test
	public void whenDeleteThenExpectedArray() {
		this.init();
		tracker.delete(item2);
		Item[] expectedArray = {item1, item3, item4, item5};
		assertArrayEquals(expectedArray, tracker.getAll());
	}
	/**
	 * Tests adding item in tracker.
	 */
	@Test
	public void whenAddThenExpectedArray() {
		this.init();
		this.tracker.delete(item2);
		this.tracker.add(this.item2);
		this.resultArray = this.tracker.getAll();
		assertArrayEquals(this.expectedArray, this.resultArray);
	}
	/**
	 * Tests updating item in tracker.
	 */
	@Test
	public void whenUpdateThenExpectedItem() {
		this.init();
		long item3Id = item3.getId();
		Date oldDate = item3.getCreate();
		Comment[] oldComments = item3.getComments();
		tracker.update(item3Id, NEW_NAME, NEW_DESC);
		String expectedName = NEW_NAME;
		String expectedDesc = NEW_DESC;
		assertEquals(expectedName, this.tracker.findById(item3Id).getName());
		assertEquals(expectedDesc, this.tracker.findById(item3Id).getDesc());
		assertEquals(oldDate, this.tracker.findById(item3Id).getCreate());
		assertArrayEquals(oldComments, this.tracker.findById(item3Id).getComments());
	}
}