package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QuadrateArrayTest {
	@Test
	public void whenInitialArrayThenProcessedArrayIsTurned(){
		int[][] initialArray={{1,2,3,4},{1,2,3,4},{1,2,3,4},{1,2,3,4}};
		int[][] expectedArray={{1,1,1,1},{2,2,2,2},{3,3,3,3},{4,4,4,4}};
		QuadrateArray quadrateArray=new QuadrateArray(initialArray);
		quadrateArray.rotate();
		for (int i=0; i<initialArray.length; i++){
			for (int j=0; j<initialArray.length; j++){
				assertThat(expectedArray[i][j], is(quadrateArray.quadrateArray[i][j]));
			}
		}
	}
}