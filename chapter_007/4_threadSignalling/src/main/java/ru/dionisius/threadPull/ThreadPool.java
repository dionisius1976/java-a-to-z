package ru.dionisius.threadPull;

import java.util.LinkedList;

/**
 * Created by Dionisius on 23.03.2017.
 * Thread pool class.
 */
public class ThreadPool {
    /**
     * The number of threads for thread pool.
     * It is equal to number of processors for current computer.
     */
    private final int nThreads;
    /**
     * The poll of threads.
     */
    private final Pool[] threads;
    /**
     * The queue for threads.
     */
    private final LinkedList queue;

    /**
     * Constructor.
     * Initializes variables and fill the thread pool with appropriate number of threads.
     */
    public ThreadPool() {
        this.nThreads = Runtime.getRuntime().availableProcessors();
        this.threads = new Pool[this.nThreads];
        this.queue = new LinkedList();
        for (int i = 0; i < this.nThreads; i++) {
            this.threads[i] = new Pool();
            this.threads[i].start();
        }
    }

    /**
     * Executes specified runnable task.
     * @param r runnable task.
     */
    public void execute(Runnable r) {
        synchronized (this.queue) {
            this.queue.addLast(r);
            this.queue.notify();
        }
    }

    /**
     * Starts the first thread in the pool of threads and remove it from the queue.
     */
    private class Pool extends Thread {
        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    r = (Runnable) queue.removeFirst();
                }
                try {
                    r.run();
                } catch (RuntimeException re) {
                    re.printStackTrace();
                }
            }
        }
    }
}
