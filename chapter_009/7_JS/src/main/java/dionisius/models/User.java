package dionisius.models;

import java.sql.Timestamp;

/**
 * Created by Dionisius on 05.08.2017.
 * This class stores user's data,
 * has getters for this data
 * and overrides toString() method.
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
     * User's country.
     */
    private final String country;
    /**
     * User's city.
     */
    private final String city;
    /**
     * The date of user's entry creation.
     */
    private final Timestamp createDate;

    /**
     * Constructor.
     * @param name user's name.
     * @param login user's login.
     * @param email user's email;
     * @param country user's country;
     * @param city user's city;
     * @param createDate creation date of user's entry.
     */
    public User(final String name, final String login, final String email, final String country,
                final String city, final Timestamp createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.country = country;
        this.city = city;
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
     * Getter for user's country.
     * @return user's country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Getter for user's city.
     * @return user's city.
     */
    public String getCity() {
        return city;
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
                + ", country='" + country + '\''
                + ", city='" + city + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
