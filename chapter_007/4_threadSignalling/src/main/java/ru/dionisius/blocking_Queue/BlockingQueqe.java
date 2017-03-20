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
     * The maximum a.
     */
    private final int maxElementsCount;

    public BlockingQueqe(int maxElementsCount) {
        this.maxElementsCount = maxElementsCount;
    }

    /**
     * Adds specified element to the queue.
     * @param e specified element.
     */
    public synchronized void add(E e) {
            while (this.queue.size() == this.maxElementsCount) {
                try {
                    wait();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            if (this.queue.size() == 0) {
                notifyAll();
            }
            System.out.printf("Thread %s. Adding object %s%s", Thread.currentThread().getName(), e, System.lineSeparator());
            this.queue.addFirst(e);
    }

    /**
     * Returns and deletes tha last element in the queue.
     */
    public synchronized E get() {
        E returningElement = null;
            while (this.queue.isEmpty()) {
                try {
                    this.queue.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            if (this.queue.size() == this.maxElementsCount)  {
                notifyAll();
            }
            System.out.printf("Thread %s. Getting object %s%s", Thread.currentThread().getName(), returningElement, System.lineSeparator());
            returningElement = this.queue.pollFirst();
        return returningElement;
    }

    /**
     * Starts the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {

        BlockingQueqe<String> queqe = new BlockingQueqe<>(10);

        Thread producer =  new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    queqe.add(String .valueOf(i));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread customer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    queqe.get();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();
        customer.start();

    }
}
