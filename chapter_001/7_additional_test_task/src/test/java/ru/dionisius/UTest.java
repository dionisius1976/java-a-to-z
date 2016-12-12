package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UTest {
	@Test
	public void whenFirstArrayIsLongerThenExpectedArray(){
		int[] arr1={0, 2, 3, 4};
		int[] sortedArr2={-3, -1, 0};
		int[] expectedArray={-3, -1, 0, 2, 3, 4};
		UnitedSortedArray unitedArray=new UnitedSortedArray();
		int[] resultArray=unitedArray.sortedArray(expectedArray, sortedArr2);
		assertArrayEquals(expectedArray, resultArray);
	}
	@Test
	public void whenFirstArrayIsShorterThenExpectedArray(){
		int[] arr1={-3, -1, 5};
		int[] sortedArr2={0, 2, 3, 4};
		int[] expectedArray={-3, -1, 0, 2, 3, 4, 5};
		UnitedSortedArray unitedArray=new UnitedSortedArray();
		int[] resultArray=unitedArray.sortedArray(expectedArray, sortedArr2);
		assertArrayEquals(expectedArray, resultArray);
	}
	@Test
	public void whenFirstArrayIsEqualSecondArrayThenSecondArray(){
		int[] arr1={0, 2, 3, 4};
		int[] sortedArr2={0, 2, 3, 4};
		int[] expectedArray={0, 2, 3, 4};
		UnitedSortedArray unitedArray=new UnitedSortedArray();
		int[] resultArray=unitedArray.sortedArray(expectedArray, sortedArr2);
		assertArrayEquals(expectedArray, resultArray);
	}
}