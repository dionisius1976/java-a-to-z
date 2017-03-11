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
    public void add(final User newUser) {
        synchronized (this.users) {
            this.users.put(newUser.getId(), newUser);
        }
    }

    /**
     * Returns user with specified user's id.
     * @param userId specified user's id.
     * @return user with specified id.
     */
    public User getUserById(final long userId) {
        synchronized (this.users) {
           return this.users.get(userId);
        }
    }

    /**
     * Sets user's new age.
     * @param userId user's id.
     * @param newAge user's new age.
     */
    public void setUsersAge(final long userId, int newAge) {
        synchronized (this.users) {
            this.users.get(userId).setAge(newAge);
        }
    }

    /**
     * Deletes user with specified id.
     * @param id user's id.
     */
    public void deleteUser(final long id) {
        synchronized (this.users) {
            this.users.remove(id);
        }
    }

    /**
     * Transfers specified amount of money from one user to another.
     * @param userDonorId the user form whom money will be transferred.
     * @param userRecipientId the user to whom money will be transferred.
     * @param amount amount of money.
     * @return true if transfer was success and false if not.
     */
    public boolean transferMoney(final long userDonorId, final long userRecipientId, int amount) {
        boolean result = true;
        synchronized (this.users.get(userDonorId)) {
            if (this.users.get(userDonorId).getAmount() < amount) {
                result = false;
            }
            synchronized (this.users.get(userRecipientId)) {
                if (this.users.get(userDonorId).getAmount() >= amount) {
                    int newAmount = amount + this.users.get(userRecipientId).getAmount();
                    this.users.get(userRecipientId).setAmount(newAmount);
                    newAmount = this.users.get(userDonorId).getAmount() - amount;
                    this.users.get(userDonorId).setAmount(newAmount);
                }
            }
        }
        return result;
    }
}
