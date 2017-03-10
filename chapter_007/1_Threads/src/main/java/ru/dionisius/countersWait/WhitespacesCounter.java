package ru.dionisius.countersWait;

/**
 * Created by Dionisius on 09.03.2017.
 * Class for counting the whitespaces in specified sentence.
 */
public class WhitespacesCounter implements Runnable {
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
        int position = 0;
        char[] sentenceArray = this.sentence.toCharArray();
            while (position < sentenceArray.length) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.format("Thread %s is interrupted!%s", Thread.currentThread().getName(), System.lineSeparator());
                    Thread.currentThread().interrupt();
                }
                if (this.sentence.charAt(position) == ' ') {
                    counter++;
                }
                position++;
            }
        return counter;
    }

    @Override
    public void run() {
        System.out.format("There is %d whitespaces in this sentence.%s",this.getWhitespacesNumber(), System.lineSeparator());
    }
}
