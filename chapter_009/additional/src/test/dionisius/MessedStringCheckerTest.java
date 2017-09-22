package dionisius;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dionisius on 20.09.2017.
 * Tests MessedStringChecker class.
 */
public class MessedStringCheckerTest {
    private static final String ORIGINAL_STRING = "abcdefg";
    private final MessedStringChecker checker = new MessedStringChecker();

    @Test
    public void whenMessedStringIsMessedOriginalThenTrue() throws Exception {
        String messedString = "cadbgfe";
        boolean expectedValue = true;
        boolean resultValue = this.checker.areStringsMessed(ORIGINAL_STRING, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

    @Test
    public void whenMessedStringIsMessedNotOriginalThenFalse() throws Exception {
        String messedString = "lkjmnut";
        boolean expectedValue = false;
        boolean resultValue = this.checker.areStringsMessed(ORIGINAL_STRING, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

    @Test
    public void whenMessedStringIsTheSameAsOriginalThenFalse() throws Exception {
        String messedString = ORIGINAL_STRING;
        boolean expectedValue = false;
        boolean resultValue = this.checker.areStringsMessed(ORIGINAL_STRING, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

    @Test
    public void whenMessedStringShorterThenFalse() throws Exception {
        String messedString = "cadbgf";
        boolean expectedValue = false;
        boolean resultValue = this.checker.areStringsMessed(ORIGINAL_STRING, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

    @Test
    public void whenMessedStringIsLongerThenFalse() throws Exception {
        String messedString = "cadbgfee";
        boolean expectedValue = false;
        boolean resultValue = this.checker.areStringsMessed(ORIGINAL_STRING, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

    @Test
    public void whenMessedStringIsEmptyThenFalse() throws Exception {
        String messedString = "";
        boolean expectedValue = false;
        boolean resultValue = this.checker.areStringsMessed(ORIGINAL_STRING, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

    @Test
    public void whenCheckedStringsAreEmptyThenFalse() throws Exception {
        String messedString = "";
        String emptyString = "";
        boolean expectedValue = false;
        boolean resultValue = this.checker.areStringsMessed(emptyString, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

    @Test
    public void whenCheckedStringsIsNotMessedButAnotherThenFalse() throws Exception {
        String messedString = "laa";
        String origString = "lla";
        boolean expectedValue = false;
        boolean resultValue = this.checker.areStringsMessed(origString, messedString);
        Assert.assertEquals(expectedValue, resultValue);
    }

}