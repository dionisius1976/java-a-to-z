package ru.dionisius;
/**
 *  This class unites two sorted arrays of integers in one sorted array of integers.
 */
public class UnitedSortedArray {
	/**
	 * First specified array.
	 */
	private final int[] first;
	/**
	 *  Second specified array.
	 */
	private final int[] second;
	/**
	 * United sorted array.
	 */
	private int[] unitedArr;
	/**
	 * Defaul constructor.
	 * @param first specified array.
	 * @param second second specified array.
	 */
	public UnitedSortedArray(final int[] first, final int[] second) {
		this.first = first;
		this.second = second;
	}
	/**
	 * Getter for united array.
	 * @return united array.
	 */
	public int[] getUnitedArr() {
		return unitedArr;
	}
	/**
	 * Unites two sorted arrays of integers.
	 */
	public void unite() {
		this.unitedArr = new int[first.length + second.length];
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		while (true) {
			if ((index1 == this.first.length) || (index2 == this.second.length)) {
				break;
			}
			if (this.first[index1] < this.second[index2]) {
				this.unitedArr[index3] = this.first[index1];
				index1++;
				index3++;
				continue;
			}
			if (this.first[index1] > this.second[index2]) {
				this.unitedArr[index3] = this.second[index2];
				index2++;
				index3++;
				continue;
			} else {
				this.unitedArr[index3] = this.second[index2];
				index3++;
				this.unitedArr[index3] = this.second[index2];
				index1++;
				index2++;
				index3++;
				continue;
			}
		}
		if (index1 == this.first.length) {
			for (int i = index2; i < this.second.length; i++) {
				this.unitedArr[index3] = this.second[i];
				index3++;
			}
		} else {
			for (int i = index1; i < this.first.length; i++) {
				this.unitedArr[index3] = this.first[i];
				index3++;
			}
		}
	}
}