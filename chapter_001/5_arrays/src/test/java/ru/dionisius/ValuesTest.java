package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValuesTest {
	@Test
	public void whenInitialArrayThenProcessedArrayIsSorted(){
		int[] initialArr=new int[]{4, -7, 20, 0, 5};
		int[] expectedArr=new int[]{-7, 0, 4, 5, 20};
		Values values=new Values(initialArr);
		values.bubbleSorting();
		for (int i=0; i<initialArr.length; i++){
			assertThat(expectedArr[i], is(values.values[i]));
		}
	}
}