package ru.dionisius;

import java.util.Collection;

/**
 * Created by Dionisius on 26.07.2017.
 * Interface for all classes that works
 * with Job classes collections.
 */
public interface IJobManager {
    /**
     * Adds specified Job class collection to specified store.
     * @param jobs specified Job class collection.
     */
    void add(Collection<Job> jobs);

    /**
     * Deletes specified Job class collection from specified store.
     * @param jobs specified Job class collection.
     */
    void delete(Collection<Job> jobs);
}
