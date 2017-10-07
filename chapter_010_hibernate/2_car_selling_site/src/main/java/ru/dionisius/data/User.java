package ru.dionisius.data;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dionisius on 29.09.2017.
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
    /**
     * The date of user's entry creation.
     */
    private Timestamp createDate;
    /**
     * User's advertisements.
     */
    private List<Ad> adList;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

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

    public List<Ad> getAdList() {
        List<Ad> result = null;
        if (this.adList != null && this.adList.size() != 0) {
            result = new ArrayList<>();
            for (Ad ad : adList) {
                if (ad instanceof HibernateProxy) {
                    Hibernate.initialize(ad);
                    ad = (Ad) ((HibernateProxy) ad)
                            .getHibernateLazyInitializer()
                            .getImplementation();
                    result.add(ad);
                }
            }
        }
        return result;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
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
//                ", adList=" + adList +
                '}';
    }
}
