package ru.dionisius;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests method rotate() of QuadrateArray class.
 */
public class QuadrateArrayTest {
	/**
	 * Testing initial array.
	 */
	static final int[][] INITIAL_ARRAY = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
	/**
	 * Expected result array.
	 */
	static final int[][] EXPECTED_ARRAY = {{1, 1, 1, 1}, {2, 2, 2, 2}, {3, 3, 3, 3}, {4, 4, 4, 4}};
	/**
	 * QuadrateArray class instance variable.
	 */
	private QuadrateArray arr;
	/**
	 * Result array after rotation of initial array.
	 */
	private int[][] resultArray;
	/**
	 * Verifies if expected array after rotation of initial array equals result array.
	 */
	@Test
	public void whenInitialArrayThenProcessedArrayIsTurned() {
		this.arr = new QuadrateArray(INITIAL_ARRAY);
		this.arr.rotate();
		this.resultArray = this.arr.getQuadrateArray();
		assertArrayEquals(EXPECTED_ARRAY, this.resultArray);
	}
}