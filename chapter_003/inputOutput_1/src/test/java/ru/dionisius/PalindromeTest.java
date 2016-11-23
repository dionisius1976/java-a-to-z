package ru.dionisius;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dionisius on 23.11.2016.
 */
public class PalindromeTest {

    /**
     * @throws Exception
     */
    @Test
    public void ifWordIsPalindromeThenTrue() throws Exception {
        Palindrome palindrome = new Palindrome();
        String inputString = "Komok";
        boolean expectedValue = true;
        boolean resultValue = palindrome.isPalindrome(inputString);
        assertEquals(expectedValue, resultValue);
    }

    /**
     * @throws Exception
     */
    @Test
    public void ifWordIsNotPalindromeThenFalse() throws Exception {
        Palindrome palindrome = new Palindrome();
        String inputString = "Apple";
        boolean expectedValue = false;
        boolean resultValue = palindrome.isPalindrome(inputString);
        assertEquals(expectedValue, resultValue);
    }

}