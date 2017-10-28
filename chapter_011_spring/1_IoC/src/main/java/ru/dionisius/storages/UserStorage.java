package ru.dionisius.storages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dionisius.models.User;

import java.util.List;

/**
 * Created by Dionisius on 23.10.2017.
 * Class for storing users in specified storage.
 */
@Component
public class UserStorage {
    /**
     * IStore interface implementation instance.
     */
    private final IStorage storage;

    /**
     * Constructor.
     * @param storage IStore interface implementation instance.
     */
    @Autowired
    public UserStorage(final IStorage storage) {
        this.storage = storage;
    }

    /**
     * Adds specified user to current storage.
     * @param user specified user.
     * @return user id.
     */
    public long addUser(User user) {
        return this.storage.addUser(user);
    }

    /**
     * Returns all users from current storage.
     * @return all users from current storage.
     */
    public List<User> getAllUsers() {
       return this.storage.getAllUsers();
    }

    /**
     * Returns user with specified id.
     * @param userId specified user id.
     * @return user with specified id.
     */
    public User getUser(long userId) {
        return  this.storage.getUser(userId);
    }

    /**
     * Updates old user by specified user with same as old user id.
     * @param user new user.
     */
    public void updateUser(User user) {
        this.storage.updateUser(user);
    }

    /**
     * Deletes user with specified id.
     * @param userId specified id.
     */
    public void deleteUser(long userId) {
        this.storage.deleteUser(userId);
    }

}
