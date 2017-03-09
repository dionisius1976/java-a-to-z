package ru.dionisius.whitespacesAndordsCounters;

/**
 * Created by Dionisius on 09.03.2017.
 * Runs two threads - one for counting the whitespaces,
 * another for counting the words in specified string.
 */
public class Runner {
    /**
     * Starts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        String sentence = " Hi, my  name is Victor. I'm glad to see you!   ";
        WhitespacesCounter whitespacesCounter = new WhitespacesCounter(sentence);
        WordsCounter wordsCounter = new WordsCounter(sentence);
        new Thread(whitespacesCounter).start();
        new Thread(wordsCounter).start();
    }
}
