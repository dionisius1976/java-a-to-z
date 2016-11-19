package ru.dionisius;

import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dionisius on 17.11.2016.
 *
 */
public class NumberCheckerTest {

    /**
     * check().
     * This method checks array of bytes with odd count
     */
    @Test
    public void whenBytesCountIsEvenThenTrue() {
        String check = "22";
        byte[] byteArray = check.getBytes();
        NumberChecker nc = new NumberChecker(new ByteArrayInputStream(byteArray));
        boolean expectedValue = true;
        boolean result = nc.isNumber();
        assertEquals(expectedValue, result);
    }

    /**
     * check().
     * This method checks array of bytes with odd count
     */
    @Test
    public void whenBytesCountIsOddThenFalse() {
        String check = "333";
        byte[] byteArray = check.getBytes();
        NumberChecker nc = new NumberChecker(new ByteArrayInputStream(byteArray));
        boolean expectedValue = false;
        boolean result = nc.isNumber();
        assertEquals(expectedValue, result);
    }
}