package dionisius.models;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Dionisius on 05.08.2017.
 * This class stores user's data,
 * has getters for this data
 * and overrides toString() method.
 */
public class User {
    /**
     * User's id.
     */
    private final String id;
    /**
     * User's name.
     */
    private final String name;
    /**
     * User's surname.
     */
    private final String surname;
    /**
     * User's login.
     */
    private final String login;
    /**
     * User's password.
     */
    private final String password;
    /**
     * User's adress.
     */
    private final Address address;
    /**
     * User's role.
     */
    private final Role role;
    /**
     * List of user's music stiles.
     */
    private final List<MusicType> musicTypes;
    /**
     * The date of user's entry creation.
     */
    private final Timestamp createDate;

    /**
     * Constructor.
     * @param id user's id.
     * @param name user's name.
     * @param login user's login.
     * @param surname user's surname.
     * @param login user's login.
     * @param password user's password.
     * @param address user's password.
     * @param role user's role.
     * @param musicTypes list of user's music styles.
     * @param createDate creation date of user's entry.
     */
    public User(final String id, final String name, final String surname, final String login, final String password,
                final Address address, final Role role, final List<MusicType> musicTypes, final Timestamp createDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.address = address;
        this.role = role;
        this.musicTypes = musicTypes;
        this.createDate = createDate;
    }

    /**
     * Getter for user's id.
     * @return user's id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter for user's name.
     * @return user's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for user's surname.
     * @return user's surname.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Getter for user's login.
     * @return user's login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Getter for user's password.
     * @return user's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter for user's address.
     * @return user's address.
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Getter for user's role.
     * @return user's role.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Getter for user's role.
     * @return user's role.
     */
    public List<MusicType> getMusicTypes() {
        return this.musicTypes;
    }

    /**
     * Getter for creation date of user's entry.
     * @return creation date of user's entry.
     */
    public Timestamp getCreateDate() {
        return this.createDate;
    }

}
