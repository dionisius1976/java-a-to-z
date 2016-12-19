package ru.dionisius;
/**
 * Removes all duplicated strings from specified array of strings.
 * All empty strings instead of duplicated are moving to the end of array.
 */
public class ArrayDublicatesDelete {
	/**
	 * Specified array of strings.
	 */
	private String[] arr;
	/**
	 * Default constructor.
	 * @param arr specified array of strings.
	 */
	public ArrayDublicatesDelete(final String[] arr) {
		this.arr = arr;
	}
	/**
	 * Getter for this array.
	 * @return this araay of strings.
	 */
	public String[] getArr() {
		return arr;
	}
	/**
	 * Deletes all duplicated strings from this array.
	 * Ans then moves all empty strings to the end of this array.
	 */
	public void delete() {
		int lastIndex = this.arr.length - 1;
		for (int i = 0; i < lastIndex; i++) {
			if ("".equals(this.arr[i])) {
				continue;
			}
			String uniqString = arr[i];
			for (int j = i + 1; j <= lastIndex; j++) {
				if ("".equals(arr[j])) {
					continue;
				}
				if (uniqString.equals(arr[j])) {
					arr[j] = "";
				}
			}
		}
		moveNullsToTheEndOfArray();
	}
	/**
	 * Moves all empty strings to the end of this array.
	 */
	private void moveNullsToTheEndOfArray() {
		int lastIndex = arr.length - 1;
		for (int i = 0; i < lastIndex; i++) {
			if ("".equals(arr[i])) {
				arr[i] = arr[lastIndex];
				arr[lastIndex] = "";
				lastIndex--;
				i--;
			}
		}
	}
}