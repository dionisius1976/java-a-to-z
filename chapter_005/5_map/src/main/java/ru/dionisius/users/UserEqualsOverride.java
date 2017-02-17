package ru.dionisius.users;

import java.util.Calendar;

/**
 * Created by Dionisius on 16.02.2017.
 * User class with overwrite equals() method.
 */
public class UserEqualsOverride extends AUser{
    /**
     * Constructor.
     * @param name user's name.
     * @param children the number of user's children.
     * @param birthday the date of user's birthday.
     */
    public UserEqualsOverride(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEqualsOverride that = (UserEqualsOverride) o;

        if (this.children != that.children) return false;
        if (!this.name.equals(that.name)) return false;
        return this.birthday.equals(that.birthday);
    }
}
