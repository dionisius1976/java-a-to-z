package ru.dionisius.users;

import java.util.Calendar;

/**
 * Created by Dionisius on 16.02.2017.
 * User class with overwrite hash() method.
 */
public class UserHashOverride extends AUser {
    /**
     * Constructor.
     * @param name user's name.
     * @param children the number of user's children.
     * @param birthday the date of user's birthday.
     */
    public UserHashOverride(final String name, final int children, final Calendar birthday) {
        super(name, children, birthday);
    }
    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.children;
        result = 31 * result + this.birthday.hashCode();
        return result;
    }
}
