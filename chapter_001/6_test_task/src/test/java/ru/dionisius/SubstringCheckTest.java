package java.ru.dionisius;

import org.junit.Test;
import ru.dionisius.SubstringCheck;

import static org.junit.Assert.assertEquals;

/**
 * This class tests class SubstringCheck.
 */
public class SubstringCheckTest {
	/**
	 * Testing initial string.
	 */
	static final String INITIAL_STRING = "grammarisnotforme";
	/**
	 * Testing true substring of initial string.
	 */
	static final String TRUE_SUBSTRING = "rme";
	/**
	 * Testing false substring of initial string.
	 */
	static final String FALSE_SUBSTRING = "rmt";
	/**
	 * Expected result true.
	 */
	static final boolean EXPECTED_RESULT_TRUE = true;
	/**
	 * Expected result false.
	 */
	static final boolean EXPECTED_RESULT_FALSE = false;
	/**
	 * SubstringCheck class instance variable.
	 */
	private SubstringCheck sCheck;
	/**
	 * Method subStringCheck() result value.
	 */
	private boolean resultValue;
	/**
	 * Verifies if expected value is equals result value.
	 */
	@Test
	public void whenSubstrIsTrueThenTrue() {
		this.sCheck = new SubstringCheck(INITIAL_STRING, TRUE_SUBSTRING);
		this.resultValue = this.sCheck.subStringCheck();
		assertEquals(EXPECTED_RESULT_TRUE, this.resultValue);
	}
	/**
	 * Verifies if expected value is equals result value.
	 */
	@Test
	public void whenSubstrIsFalseThenFalse() {
		this.sCheck = new SubstringCheck(INITIAL_STRING, FALSE_SUBSTRING);
		this.resultValue = this.sCheck.subStringCheck();
		assertEquals(EXPECTED_RESULT_FALSE, this.resultValue);
	}
}