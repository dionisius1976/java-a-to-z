package ru.dionisius;

/**
 * Created by Dionisius on 04.02.2017.
 */
public class Role extends ABase {
    /**
     * Identification number for this instance.
     */
    private String id;
    /**
     * Constructor.
     * @param id Identification number for this instance.
     */
    public Role(String id) {
        super();
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id != null ? id.equals(role.id) : role.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
