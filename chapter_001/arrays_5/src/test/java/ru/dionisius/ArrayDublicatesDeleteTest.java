package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArrayDublicatesDeleteTest {
	@Test
	public void WhenThen(){
		String[] initialArray={"voice","","i","i","","joy","joy","voice","","joy"};
		String[] expectedArray={"voice","","i","","","joy","","","",""};
		ArrayDublicatesDelete arr=new ArrayDublicatesDelete(initialArray);
		arr.arrayDublicatesDelete();
		int lastIndex=arr.arr.length-1;		
		for(int i=0; i<lastIndex; i++){
			assertThat(expectedArray[i], is(arr.arr[i]));
		}
	}
}