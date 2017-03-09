package ru.dionisius.whitespacesAndWordsCounters;

/**
 * Created by Dionisius on 09.03.2017.
 * Class for counting the whitespaces in specified sentence.
 */
public class WhitespacesCounter  implements Runnable {
    /**
     * Specified sentence.
     */
    private final String sentence;
    /**
     * Constructor.
     * @param sentence specified sentence.
     */
    public WhitespacesCounter(String sentence) {
        this.sentence = sentence;
    }

    /**
     * Calculates the count of whitespaces in the string.
     * @return the count of whitespaces in the string.
     */
    private int getWhitespacesNumber() {
        int counter = 0;
        char[] sentenceArray = this.sentence.toCharArray();
        for (int i = 0; i < sentenceArray.length; i++) {
            if (this.sentence.charAt(i) == ' ')  {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void run() {
        System.out.format("There is %d whitespaces in this sentence.%s",this.getWhitespacesNumber(), System.lineSeparator());
    }
}
