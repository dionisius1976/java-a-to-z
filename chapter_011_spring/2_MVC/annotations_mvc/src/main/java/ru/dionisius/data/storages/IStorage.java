package ru.dionisius.data.storages;

import ru.dionisius.data.models.User;

import java.util.List;

/**
 * Created by Dionisius on 23.10.2017.
 * Interface for all users storages.
 */
public interface IStorage {
    /**
     * Adds specified user to storage.
     * @param user specified user.
     * @return user's id.
     */
    long addUser(User user);

    /**
     * Returns list of all user from storage.
     * @return list of all user from storage.
     */
    List<User> getAllUsers();

    /**
     * Returns user with specified id.
     * @param userId specified id.
     * @return user with specified id.
     */
    User getUser(long userId);

    /**
     * Updates old user by specified user with same as old user id.
     * @param user new user.
     */
    void updateUser(User user);

    /**
     * Deletes user with specified id.
     * @param userId specified id.
     */
    void deleteUser(long userId);
}
