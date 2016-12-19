package ru.dionisius;
/**
 * This class rotates 180 degrees quadratic two-dimensional array of integers.
 */
public class QuadrateArray {
	/**
	 * Specified  quadratic two-dimensional array.
	 */
	private int[][] quadrateArray;
	/**
	 * Default constructor.
	 * @param quadrateArray specified  quadratic two-dimensional array.
	 */
	public QuadrateArray(final int[][] quadrateArray) {
		this.quadrateArray = quadrateArray;
	}
	/**
	 * Getter for this array.
	 * @return this array.
	 */
	public int[][] getQuadrateArray() {
		return quadrateArray;
	}
	/**
	 * Rotates this array 180 degrees.
	 */
	public void rotate() {
		int[][] temp = new int[this.quadrateArray.length][this.quadrateArray.length];
		for (int i = 0; i < this.quadrateArray.length; i++) {
			for (int j = 0; j < this.quadrateArray.length; j++) {
				temp[j][i] = this.quadrateArray[i][j];
			}
		}
		this.quadrateArray = temp;
	}
}
