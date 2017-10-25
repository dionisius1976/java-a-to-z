package ru.dionisius.storages;

import org.springframework.stereotype.Component;
import ru.dionisius.models.User;

import java.util.*;

/**
 * Created by Dionisius on 23.10.2017.
 *
 * Storage for store and handle users collection.
 */
@Component
public class MemoryStorage implements IStorage {
    /**
     * Inner storage for users.
     */
    private Map<Long, User> storage = new TreeMap<>();
    /**
     * User's id.
     */
    private long id = 0;

    @Override
    public long addUser(User user) {
        long userId = -1;
        if (!this.isUserExist(user)) {
            user.setId(++this.id);
            this.storage.put(user.getId(), user);
            userId = user.getId();
        }
        return userId;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(this.storage.values());
    }

    @Override
    public User getUser(long userId) {
        return this.storage.get(userId);
    }

    @Override
    public void updateUser(User user) {
        this.storage.replace(user.getId(), user);
    }

    @Override
    public void deleteUser(long userId) {
        this.storage.remove(userId);
    }
    /**
     * Checks if specified user exists in storage.
     * @param user specified user.
     * @return true if specified user exists, false if not.
     */
    private boolean isUserExist(final User user) {
        boolean result = false;
        String userLogin = user.getLogin();
        for(User currentUser: this.storage.values()) {
            if (currentUser.getLogin().equals(userLogin)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
