package ru.dionisius.users;

import java.util.Calendar;

/**
 * Created by Dionisius on 16.02.2017.
 */
public class UserHashAndEqualsOverride extends AUser{
    /**
     * Constructor.
     * @param name user's name.
     * @param children the number of user's children.
     * @param birthday the date of user's birthday.
     */
    public UserHashAndEqualsOverride(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        UserHashAndEqualsOverride that = (UserHashAndEqualsOverride) o;

        if (this.children != that.children) return false;
        if (!name.equals(that.name)) return false;
        return this.birthday.equals(that.birthday);
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + children;
        result = 31 * result + birthday.hashCode();
        return result;
    }
}
