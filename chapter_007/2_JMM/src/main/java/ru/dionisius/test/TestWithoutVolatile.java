package ru.dionisius.test;

/**
 * Created by Dionisius on 11.03.2017.
 * Illustrates wrong working of this program.
 * If the variable is not volatile
 * ChangeListener loops infinitely.
 */
public class TestWithoutVolatile {
    /**
     * Variable for volatile feature testing.
     */
    private static int MY_INT = 0;

    /**
     * Starts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        new TestWithVolatile.ChangeListener().start();
        new TestWithVolatile.ChangeMaker().start();
    }

    /**
     * Listens the variable changing and prints message if it has been changed.
     */
    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while ( local_value < 5){
                if( local_value!= MY_INT){
                    System.out.println("Got Change for MY_INT : " + MY_INT);
                    local_value = MY_INT;
                }
            }
        }
    }

    /**
     * Increments the variable five times.
     */
    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while (MY_INT <5){
                System.out.println("Incrementing MY_INT " + (local_value + 1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
