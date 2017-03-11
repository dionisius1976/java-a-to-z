package ru.dionisius.countersWait;

/**
 * Created by Dionisius on 09.03.2017.
 * Class for counting the words in specified sentence.
 */
public class WordsCounter implements Runnable {

    /**
     * Specified sentence.
     */
    private final String sentence;

    /**
     * Constructor.
     * @param sentence specified sentence.
     */
    public WordsCounter(final String sentence) {
        this.sentence = sentence;
    }

    /**
     * Calculates the count of words in the string.
     * @return the count of words in the string.
     */
    private int getWordsNumber() {
        int wordCounter = 0;
        int position = 0;
        while (position < this.sentence.length()) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.format("Thread %s is interrupted!%s", Thread.currentThread().getName(), System.lineSeparator());
                Thread.currentThread().interrupt();
            }
            if (this.sentence.charAt(position) != ' ') {
                for (int i = position; i < this.sentence.length(); i++) {
                    if (this.sentence.charAt(i) == ' ' || i == this.sentence.length() - 1) {
                        wordCounter++;
                        position = i;
                        break;
                    }
                }
            }
            position++;
        }
        return wordCounter;
    }

    @Override
    public void run() {
        System.out.format("There is %d words in this sentence.%s", this.getWordsNumber(), System.lineSeparator());
    }
}
