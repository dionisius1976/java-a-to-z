package ru.dionisius;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests UnitedSortedArray class.
 */
public class UnitedSortedArrayTest {
	/**
	 * Testing initial first array.
	 */
	static final int[] FIRST_INITIAL_ARRAY = {0, 2, 3, 4};
	/**
	 * Testing initial second array.
	 */
	static final int[] SECOND_INITIAL_ARRAY = {-3, -1, 0};
	/**
	 * Testing initial third array.
	 */
	static final int[] THIRD_INITIAL_ARRAY = {5, 16, 1000};
	/**
	 * Expected result array.
	 */
	static final int[] EXPECTED_ARRAY = {-3, -1, 0, 0, 2, 3, 4};
	/**
	 * Second expected result array.
	 */
	static final int[] SECOND_EXPECTED_ARRAY = {-3, -1, 0, 5, 16, 1000};
	/**
	 * UnitedSortedArray class instance variable.
	 */
	private UnitedSortedArray arr;
	/**
	 * Result array after rotation of initial array.
	 */
	private int[] resultArray;
	/**
	 * Verifies if united array equals expected array if the first array longer than the second.
	 */
	@Test
	public void whenFirstArrayIsLongerThenExpectedArray() {
		this.arr = new UnitedSortedArray(FIRST_INITIAL_ARRAY, SECOND_INITIAL_ARRAY);
		this.arr.unite();
		this.resultArray = this.arr.getUnitedArr();
		assertArrayEquals(EXPECTED_ARRAY, this.resultArray);
	}

	/**
	 * Verifies if united array equals expected array if the second array longer than the first.
	 */
	@Test
	public void whenFirstArrayIsShorterThenExpectedArray() {
		this.arr = new UnitedSortedArray(SECOND_INITIAL_ARRAY, FIRST_INITIAL_ARRAY);
		this.arr.unite();
		this.resultArray = this.arr.getUnitedArr();
		assertArrayEquals(EXPECTED_ARRAY, this.resultArray);
	}

	/**
	 * Verifies if united array equals expected array if length of each of two arrays is the same.
	 */
	@Test
	public void whenFirstArrayIsEqualSecondArrayThenSecondArray() {
		this.arr = new UnitedSortedArray(SECOND_INITIAL_ARRAY, THIRD_INITIAL_ARRAY);
		this.arr.unite();
		this.resultArray = this.arr.getUnitedArr();
		assertArrayEquals(SECOND_EXPECTED_ARRAY, this.resultArray);
	}
}