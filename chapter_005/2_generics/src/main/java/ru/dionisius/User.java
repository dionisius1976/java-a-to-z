package ru.dionisius;

/**
 * Created by Dionisius on 04.02.2017.
 *
 */
public class User extends ABase {
    /**
     * Identification number for this instance.
     */
    private String id;
    /**
     * Constructor.
     * @param id Identification number for this instance.
     */
    public User(String id) {
        super();
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
