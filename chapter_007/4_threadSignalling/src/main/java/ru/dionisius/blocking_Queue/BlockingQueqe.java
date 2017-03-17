package ru.dionisius.blocking_Queue;

import java.util.LinkedList;

/**
 * Created by Dionisius on 15.03.2017.
 */
public class BlockingQueqe<E> {
    /**
     * Blocking queue.
     */
    private final LinkedList<E> queue = new LinkedList<>();
    /**
     * Flag for queue state.
     */
    private boolean isLocked = true;

    /**
     * Adds specified element to the queue.
     * @param e specified element.
     */
    public void add(E e) {
        synchronized (this.queue) {
            System.out.printf("Thread %s. Adding object %s%s", Thread.currentThread().getName(), e, System.lineSeparator());
            this.queue.addFirst(e);
        this.isLocked = false;
            this.queue.notify();
        }
    }

    /**
     * Returns and deletes tha last element in the queue.
     */
    public E get() {
        E returningElement = null;
        synchronized (this.queue) {
            while (this.isLocked) {
                try {
                    this.queue.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

            }
            while (!this.queue.isEmpty()) {
                returningElement = this.queue.pollFirst();
                System.out.printf("Thread %s. Getting object %s%s", Thread.currentThread().getName(), returningElement, System.lineSeparator());
            }
            this.isLocked = true;
        }
        return returningElement;
    }

    /**
     * Stsrts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {

        BlockingQueqe<String> queqe = new BlockingQueqe<>();

        Thread producer =  new Thread() {
            @Override
            public void run() {
                    queqe.add("Hello!");
                }
        };

        Thread customer = new Thread() {
            @Override
            public void run() {
                queqe.get();
            }
        };

        producer.start();
        customer.start();

    }
}
