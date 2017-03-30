package ru.dionisius.countersWait;

/**
 * Created by Dionisius on 09.03.2017.
 */
public class Runner {
    /**
     * Starts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) throws InterruptedException {
        String sentence = " Hi, my  name is Victor. I'm glad to see you!   ";
        WhitespacesCounter whitespacesCounter = new WhitespacesCounter(sentence);
        WordsCounter wordsCounter = new WordsCounter(sentence);
        Thread first = new Thread(whitespacesCounter);
        Thread second = new Thread(wordsCounter);
        System.out.println("Program is started.");
        long startTime = System.currentTimeMillis();
        long patienceTime = 10;
        first.start();
        System.out.println("First is started.");
        second.start();
        System.out.println("Second is started.");
        while (first.isAlive() && second.isAlive()) {
            System.out.println("waiting...");
            first.join(100);
            second.join(100);
            if (System.currentTimeMillis() - startTime > patienceTime) {
                if (first.isAlive()) {
                    first.interrupt();
                    first.join();
                }
                if (second.isAlive()) {
                    second.interrupt();
                    second.join();
                }
            }
        }
        System.out.format("Program is completed.%s", System.lineSeparator());
    }
}
