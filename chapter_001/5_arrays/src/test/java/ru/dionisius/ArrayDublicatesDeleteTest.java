package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArrayDublicatesDeleteTest {
	@Test
	public void whenInitialArrayHasDublicatesThenProcessedArrayHasNoDublicates(){
		String[] initialArray={"voice","","i","i","","joy","joy","voice","hi","joy"};
		ArrayDublicatesDelete arr=new ArrayDublicatesDelete(initialArray);
		arr.arrayDublicatesDelete();
		String[] expectedArray={"voice","hi","i","joy","","","","","",""};
		String[] processedArray=arr.arr;
		assertArrayEquals(expectedArray, processedArray);
	}
}