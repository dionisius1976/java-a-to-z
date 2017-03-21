package ru.dionisius.myLock;

/**
 * Created by Dionisius on 20.03.2017.
 *  Simple Lock implementation.
 */
public class MyLock implements ILock {

    /**
     * Flag of lock state.
     */
    private boolean isLocked = false;

    @Override
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isLocked = true;
    }

    @Override
    public synchronized void unlock() {
        while (!isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isLocked = true;
        notify();
    }
}
