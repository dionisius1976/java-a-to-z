package ru.dionisius.myLock;

/**
 * Created by Dionisius on 20.03.2017.
 * Lock interface.
 */
public interface ILock {
    /**
     * Locks the lock.
     */
    void lock();

    /**
     * Unlocks the lock.
     */
    void unlock();
}
