package ru.dionisius.model;

import java.sql.Timestamp;

/**
 * Created by Dionisius on 05.08.2017.
 * This class stores user's data
 * and has getters for this data.
 */
public class User {
    /**
     * User's name.
     */
    private final String name;
    /**
     * User's login.
     */
    private final String login;
    /**
     * User's email.
     */
    private final String email;
    /**
     * The date of user's entry creation.
     */
    private final Timestamp createDate;

    /**
     * Constructor.
     * @param name user's name.
     * @param login user's login.
     * @param email user's email;
     * @param createDate creation date of user's entry.
     */
    public User(final String name, final String login, final String email,
                final Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Getter for user's name.
     * @return user's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for user's login.
     * @return user's login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Getter for user's email.
     * @return user's email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter for creation date of user's entry.
     * @return creation date of user's entry.
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
