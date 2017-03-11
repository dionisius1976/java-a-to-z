package ru.dionisius.user;

/**
 * Created by Dionisius on 11.03.2017.
 * Class User.
 */
public class User {
    /**
     * User's name.
     */
    private final String name;
    /**
     * User's surname.
     */
    private final String surname;
    /**
     * User's age.
     */
    private int age;
    /**
     * User's id.
     */
    private final long id;
    /**
     * User's money amount.
     */
    private int amount;

    /**
     * Constructor.
     * @param name user's name.
     * @param surname user's surname.
     * @param age user's age.
     * @param id user's id.
     * @param amount user's money amount.
     */
    public User(final String name, final String surname, final int age, final long id, final int amount) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.id = id;
        this.amount = amount;
    }

    /**
     * Getter for user's name.
     * @return user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for user's surname.
     * @return user's surname.
     */
    public String getSurame() {
        return surname;
    }

    /**
     * Gettr for user's age.
     * @return user's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter for user's id.
     * @return user's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for user's money amount.
     * @return user's money amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Setter for user's money amount.
     * @param amount user's money amount.
     */
    public void setAmount(final int amount) {
        this.amount = amount;
    }

    /**
     * Setter for user's age.
     * @param age users new age.
     */
    public void setAge(int age) {
        this.age = age;
    }
}
