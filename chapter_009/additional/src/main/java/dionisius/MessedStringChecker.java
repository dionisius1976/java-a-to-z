package dionisius;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dionisius on 05.08.2017.
 * This class has method to check if messed string is indeed messed original string.
 */
public class MessedStringChecker {
    /**
     * Counts unique characers from original string in original string and in messed string.
     * If all counts are equal and messed string is not equal original string
     * and no of strings are empty them messed string is indeed messed original string.
     * @param originalString
     * @param messedString
     * @return
     */
    public boolean areStringsMessed(final String originalString, final String messedString) {
        boolean result = false;
        if (originalString.length() == messedString.length() && !originalString.equals(messedString)) {
            boolean checkFlag = true;
            Set<Character> checkedChars = new HashSet<>();
            char[] originalArray = originalString.toCharArray();
            char[] messedlArray = messedString.toCharArray();
            for (int i = 0; i < originalString.length(); i++) {
                if(!checkedChars.contains(originalArray[i])) {
                    if (this.charAmountInArray(originalArray[i], originalArray) ==
                            this.charAmountInArray(originalArray[i], messedlArray)) {
                        checkedChars.add(originalArray[i]);
                    } else {
                        checkFlag = false;
                        break;
                    }
                }
            }
            if (checkFlag) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns the ammount of specified character in specified array of characters.
     * @param checkingChar specified character.
     * @param checkedArray specified array of characters.
     * @return the ammount of specified character in specified array of characters.
     */
    private int charAmountInArray(final char checkingChar, final char[] checkedArray) {
        int resultAmount = 0;
        for (int i = 0; i < checkedArray.length; i++) {
            if (checkingChar == checkedArray[i]) {
                    resultAmount++;
            }
        }
        return resultAmount;
    }
}
