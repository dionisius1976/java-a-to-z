package ru.dionisius.Users;

import java.util.Calendar;

/**
 * Created by Dionisius on 16.02.2017.
 * Abstract class for all User classes.
 */
public abstract class AUser {
    /**
     * User's name.
     */
    public String name;
    /**
     * The number of user's children.
     */
    public int children;
    /**
     * The date of user's birthday.
     */
    public final Calendar birthday;

    /**
     * Constructor.
     * @param name user's name.
     * @param children the number of user's children.
     * @param birthday the date of user's birthday.
     */
    public AUser(final String name, final int children, final Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
