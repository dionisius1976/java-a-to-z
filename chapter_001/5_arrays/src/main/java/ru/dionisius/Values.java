package ru.dionisius;

/**
 * This class provides bubble sorting of specified array of integers.
 */
public class Values {

	/**
	 * Specified array of integers.
	 */
	private int[] arr;

	/**
	 * Default constructor.
	 * @param arr specified array of integers.
	 */
	public Values(final int[] arr) {
		this.arr = arr;
	}

	/**
	 * Getter for this array.
	 * @return this array.
	 */
	public int[] getArr() {
		return arr;
	}

	/**
	 * Provides bubble sorting of specified array of integers.
	 */
	public void bubbleSorting() {
		for (int i = this.arr.length - 1; i > 0; i--) {
			maxToEnd(i);
		}
	}

	/**
	 * Moves the biggest value of specified area of this array to the end of this specified area.
	 * @param lastIndex last index of specified area of this array.
	 */
	private void maxToEnd(int lastIndex) {
		int temp = 0;
		for (int i = 0; i < lastIndex; i++) {
			if (this.arr[i] > this.arr[i + 1]) {
				temp = this.arr[i + 1];
				this.arr[i + 1] = this.arr[i];
				this.arr[i] = temp;
				this.arr[i] = temp;
			}
		}
	}
}