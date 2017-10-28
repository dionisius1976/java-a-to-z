package ru.dionisius.models;

/**
 * Created by Dionisius on 23.10.2017.
 */
public class User {
    /**
     * Car's id.
     */
    private long id;
    /**
     * User's login.
     */
    private String login;
    /**
     * User's password.
     */
    private String password;
    /**
     * User's name.
     */
    private String name;
    /**
     * User's surname.
     */
    private String surname;
    /**
     * User's telephone number.
     */
    private String telNumber;

    public User(){}

    public User(String login, String password, String name, String surname,
                String telNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.telNumber = telNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telNumber='" + telNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (!password.equals(user.password)) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        return telNumber.equals(user.telNumber);
    }

}
