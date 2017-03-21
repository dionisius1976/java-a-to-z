package ru.dionisius.user;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dionisius on 11.03.2017.
 * Storage for users.
 */
public class UserStorage {
    /**
     * Storage of users.
     */
    private Map<Long, User> users = new HashMap<>();

    /**
     * Adds specified new user in storage of users.
     * @param newUser specified new user.
     */
    public synchronized void add(final User newUser) {
            this.users.put(newUser.getId(), newUser);
    }

    /**
     * Returns user with specified user's id.
     * @param userId specified user's id.
     * @return user with specified id.
     */
    public synchronized User getUserById(final long userId) {
           return this.users.get(userId);
    }

    /**
     * Sets user's new age.
     * @param userId user's id.
     * @param newAge user's new age.
     */
    public synchronized void setUsersAge(final long userId, int newAge) {
            this.users.get(userId).setAge(newAge);
    }

    /**
     * Deletes user with specified id.
     * @param id user's id.
     */
    public synchronized void deleteUser(final long id) {
            this.users.remove(id);
    }

    /**
     * Transfers specified amount of money from one user to another.
     * @param userDonorId the user form whom money will be transferred.
     * @param userRecipientId the user to whom money will be transferred.
     * @param amount amount of money.
     * @return true if transfer was success and false if not.
     */
    public synchronized boolean transferMoney(final long userDonorId, final long userRecipientId, int amount) {
        boolean result = true;
        if (this.users.get(userDonorId).getAmount() < amount) {
            result = false;
        } else {
            if (this.users.get(userDonorId).getAmount() >= amount) {
                int newRecipientAmount = amount + this.users.get(userRecipientId).getAmount();
                this.users.get(userRecipientId).setAmount(newRecipientAmount);
                int newDonorAmount = this.users.get(userDonorId).getAmount() - amount;
                this.users.get(userDonorId).setAmount(newDonorAmount);
            }
        }
        return result;
    }
}
