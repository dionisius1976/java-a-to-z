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
    public void whenNumberIsEvenThenTrue() {
        NumberChecker nc = new NumberChecker();
        String check = "22";
        byte[] byteArray = check.getBytes();
        boolean expectedValue = true;
        boolean result = nc.isNumber(new ByteArrayInputStream(byteArray));
        assertEquals(expectedValue, result);
    }

    /**
     * check().
     * This method checks array of bytes with odd count
     */
    @Test
    public void whenNumberIsOddThenFalse() {
        NumberChecker nc = new NumberChecker();
        String check = "221";
        byte[] byteArray = check.getBytes();
        boolean expectedValue = false;
        boolean result = nc.isNumber(new ByteArrayInputStream(byteArray));
        assertEquals(expectedValue, result);
    }
}