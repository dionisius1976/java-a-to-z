package ru.dionisius;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests method bubbleSorting() of Values class.
 */
public class ValuesTest {
	/**
	 * Testing initial array.
	 */
	static final int[] INITIAL_ARRAY = {4, -7, 20, 0, 5};
	/**
	 * Expected result array.
	 */
	static final int[] EXPECTED_ARRAY = {-7, 0, 4, 5, 20};
	/**
	 * Values class instance variable.
	 */
	private Values values;
	/**
	 * Result array after rotation initial array.
	 */
	private int[] resultArray;
	/**
	 * Verifies if expected array after rotation of initial array equals result array.
	 */
	@Test
	public void whenInitialArrayThenProcessedArrayIsSorted() {
		values = new Values(INITIAL_ARRAY);
		values.bubbleSorting();
		this.resultArray = this.values.getArr();
		assertArrayEquals(EXPECTED_ARRAY, this.resultArray);
	}
}