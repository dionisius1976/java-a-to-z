package ru.dionisius;

/**
 * This class verifies if specified substring is a part of specified array of strings.
 */
public class SubstringCheck {

	/**
	 * Specified array of strings.
	 */
	private String origin;
	/**
	 * Specified substring.
	 */
	private String sub;

	/**
	 * Default constructor.
	 * @param origin specified array of strings.
	 * @param sub specified substring.
	 */
	public SubstringCheck(final String origin, final String sub) {
		this.origin = origin;
		this.sub = sub;
	}
	/**
	 * Checks if specified substring is a part of this array.
	 * @return true if substring is a part of this array and false if not.
	 */
	public boolean subStringCheck() {
		boolean isSubstring = false;
		char[] originArray = this.origin.toCharArray();
		char[] subArray = this.sub.toCharArray();
		int originLastIndex = originArray.length - subArray.length + 1;
		for (int j = 0; j <= originLastIndex; j++) {
			if (subArray[0] == originArray[j]
					&& this.isArraysEqual(subArray, 1, originArray, j + 1)) {
				isSubstring = true;
			}
		}
		return isSubstring;
	}
	/**
	 * Matches two arrays specified areas for equality.
	 * @param arr1 first array to match.
	 * @param arr1StartIndex starting index of specified area of first array.
	 * @param arr2 second array to match.
	 * @param arr2StartIndex starting index of specified area of second array.
	 * @return true if areas is equal and false if not.
	 */
	private boolean isArraysEqual(char[] arr1, int arr1StartIndex, char[] arr2, int arr2StartIndex) {
		boolean isArraysEqual = true;
		int arr1LastIndex = arr1.length - 1;
		for (int i = arr1StartIndex; i <= arr1LastIndex; i++) {
			if (arr1[i] != arr2[arr2StartIndex]) {
				isArraysEqual = false;
			}
			arr2StartIndex++;
		}
		return isArraysEqual;
	}
}