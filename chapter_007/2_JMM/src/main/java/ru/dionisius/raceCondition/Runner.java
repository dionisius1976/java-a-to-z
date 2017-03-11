package ru.dionisius.raceCondition;

/**
 * Created by Dionisius on 10.03.2017.
 * Class displays the race condition case.
 * Each of five threads increment the variable with 0 value
 * ten thousand times. In race condition case there is no
 * guarantee that result variable will be fifty thousand.
 */
public class Runner {

    /**
     * Class for incrementing specified variable ten thousand times.
     */
    public static class Changing implements Runnable {

        /**
         * Specified variable.
         */
        private VariableStorage vs;

        /**
         * Constructor.
         * @param vs specified variable.
         */
        public Changing(final VariableStorage vs) {
            this.vs = vs;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                vs.changingVariable = vs.changingVariable + 1;
            }

        }
    }

    /**
     * Starts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {

        VariableStorage vs = new VariableStorage();

        Changing ch1 = new Changing(vs);
        Changing ch2 = new Changing(vs);
        Changing ch3 = new Changing(vs);
        Changing ch4 = new Changing(vs);
        Changing ch5 = new Changing(vs);

        Thread t1 = new Thread(ch1);
        Thread t2 = new Thread(ch2);
        Thread t3 = new Thread(ch3);
        Thread t4 = new Thread(ch4);
        Thread t5 = new Thread(ch5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Result value is" + vs);
        System.out.println("Expected value is 50000.");
    }
}
