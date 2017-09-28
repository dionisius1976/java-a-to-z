package ru.dionisius;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dionisius on 05.08.2017.
 * This class has method to check if messed string is indeed messed original string.
 * This class uses hash map to check specified strings.
 */
public class HashMessedStringChecker {
    /**
     * @param originalString specified original string.
     * @param messedString specified messed string.
     * @return true if messed string is indeed messed original string.
     */
    public boolean areStringsMessed(final String originalString, final String messedString) {
        boolean result = false;
        if (originalString.length() == messedString.length() && !originalString.equals(messedString)) {
            result = this.stringCharactersAmount(originalString).equals(this.stringCharactersAmount(messedString));
        }
        return result;
    }

    /**
     * Returns map of all characters and their amount in specified string.
     * @param checkingString specified string.
     * @return map of all characters and their amount in specified string.
     */
    private Map<Character, Integer> stringCharactersAmount(final String checkingString) {
        int checkingStringLength = checkingString.length();
        Map<Character, Integer> resultMap = new HashMap<>();
        Integer amount = null;
        for (int i = 0; i < checkingStringLength; i++) {
            amount = resultMap.put(checkingString.charAt(i), 1);
            if(amount != null) {
                resultMap.put(checkingString.charAt(i), ++amount);
            }
        }
        return resultMap;
    }
}
