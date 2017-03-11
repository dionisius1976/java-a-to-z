package ru.dionisius.counter;

/**
 * Created by Dionisius on 11.03.2017.
 * Displays synchronized counter's work in threads.
 * Without synchronized block this program may work incorrect.
 */
public class CounterExample {

    /**
     * This class stores testing variable.
     */
    public final static class Counter {
        public int counter = 0;
    }

    /**
     * Class increments specified variable ten thousand times.
     */
    public final static class Increment implements Runnable {
        /**
         * Specified Counter instance reference.
         */
        private Counter counter;

        /**
         * Constructor.
         * @param counter specified Counter instance reference.
         */
        public Increment(final Counter counter) {
            this.counter = counter;
        }

        /**
         * Increments the variable ten thousand times.
         * @return incremented variable.
         */
        private int increment () {
            synchronized (this.counter) {
                for (int i = 0; i < 10000; i++) {
                    this.counter.counter++;
                }
            }
                return this.counter.counter;
        }
        @Override
        public void run() {
            System.out.println(this.increment());
        }
    }

    /**
     * Starts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        Counter counter = new Counter();
        Increment increment1 = new Increment(counter);
        Increment increment2 = new Increment(counter);
        Increment increment3 = new Increment(counter);
        Increment increment4 = new Increment(counter);
        Thread t1 = new Thread(increment1);
        Thread t2 = new Thread(increment2);
        Thread t3 = new Thread(increment3);
        Thread t4 = new Thread(increment4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
