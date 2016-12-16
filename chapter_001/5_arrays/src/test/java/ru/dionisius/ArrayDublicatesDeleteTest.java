package ru.dionisius;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 *
 */
public class ArrayDublicatesDeleteTest {
	/**
	 * Testing initial array.
	 */
	static final String[] INITIAL_ARRAY = {"voice", "", "i", "i", "", "joy", "joy", "voice", "hi", "joy"};
	/**
	 * Expected result array.
	 */
	static final String[] EXPECTED_ARRAY = {"voice", "hi", "i", "joy", "", "", "", "", "", ""};
	/**
	 * Method delete() result array.
	 */
	private String[] resultArray;
	/**
	 * ArrayDublicatesDelete class variable.
	 */
	private ArrayDublicatesDelete arrDel;

	/**
	 * Verifies if expected array after deleting duplicates from initial array equals result array.
	 */
	@Test
	public void whenInitialArrayHasDublicatesThenProcessedArrayHasNoDublicates() {
		this.arrDel = new ArrayDublicatesDelete(INITIAL_ARRAY);
		this.arrDel.delete();
		this.resultArray = this.arrDel.getArr();
		assertArrayEquals(EXPECTED_ARRAY, this.resultArray);
	}
}