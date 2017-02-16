package ru.dionisius.Users;

import java.util.Calendar;

/**
 * Created by Dionisius on 16.02.2017.
 * User class without overwrite hash() and equals() methods.
 */
public class UserNoOverride extends AUser {
    /**
     * Constructor.
     * @param name user's name.
     * @param children the number of user's children.
     * @param birthday the date of user's birthday.
     */
    public UserNoOverride(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }
}
