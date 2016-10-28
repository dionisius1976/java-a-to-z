package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SubstringCheckTest {
	@Test
	public void whenSubstrIsTrueThenTrue(){
	String origin="grammarisnotforme";
	String sub="rme";
	SubstringCheck sCheck=new SubstringCheck(origin, sub);
	assertTrue(sCheck.subStringCheck());
	}
	@Test
	public void whenSubstrIsFalseThenFalse(){
	String origin="grammarisnotforme";
	String sub="rmt";
	SubstringCheck sCheck=new SubstringCheck(origin, sub);
	assertFalse(sCheck.subStringCheck());
	}
}