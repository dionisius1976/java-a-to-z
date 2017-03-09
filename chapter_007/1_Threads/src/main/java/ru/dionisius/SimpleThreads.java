package ru.dionisius;

/**
 * Created by Dionisius on 09.03.2017.
 */
public class SimpleThreads {
    static void threadMessage(String message) {
        String threadName =
                Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                threadName,
                message);
    }
}
