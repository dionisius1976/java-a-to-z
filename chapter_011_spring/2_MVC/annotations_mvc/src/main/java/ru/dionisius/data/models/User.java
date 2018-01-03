package ru.dionisius.data.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Dionisius on 29.09.2017.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Car's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * User's login.
     */
    @Column(name = "login")
    private String login;
    /**
     * User's password.
     */
    @Column(name = "password")
    private String password;
    /**
     * User's name.
     */
    @Column(name = "name")
    private String name;
    /**
     * User's surname.
     */
    @Column(name = "surname")
    private String surname;
    /**
     * User's telephone number.
     */
    @Column(name = "tel_number")
    private String telNumber;
    /**
     * The date of user's entry creation.
     */
    @Column(name = "create_date")
    private Timestamp createDate;
    /**
     * User's advertisements.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ad> ads;
    /**
     * Default constructer.
     */
    public User() {
    }

    /**
     * Constructor with parameter.
     * @param id user's id.
     */
    public User(long id) {
        this.id = id;
    }

    /**
     * Constructor with parameter.
     * @param login user's login.
     * @param password user's password.
     * @param name user's name.
     * @param surname user's surname.
     * @param telNumber user's telephone number.
     */
    public User(String login, String password, String name, String surname,
                String telNumber) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.telNumber = telNumber;
        this.createDate = new Timestamp(System.currentTimeMillis());
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

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
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
                ", createDate=" + createDate +
                '}';
    }
}
