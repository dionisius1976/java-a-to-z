package ru.dionisius.data;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Timestamp;

/**
 * Created by Dionisius on 05.08.2017.
 * POJO.
 * This class stores task's data
 * and has getters and setters for this data.
 */
public class Ad {
    /**
     * Ad's id.
     */
    private long id;
    /**
     * Ad's login.
     */
    private String desc;
    /**
     * Ad's state.
     */
    private boolean sold;
    /**
     * The date of Ad creation.
     */
    private Timestamp createDate;

    private User user;

    private Car car;

    public Ad() {
    }

    public Ad(long id) {
        this.id = id;
    }

    public Ad(String desc, User user, Car car) {
        this.desc = desc;
        this.sold = false;
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.user = user;
        this.car = car;
    }

    /**
     * Setter for id of this item.
     * @param id id of this item.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Setter for description of this item.
     * @param desc the description of this item.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter for "is done" state for this item.
     * @param sold "is done" state for this item.
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    /**
     * Setter for creation date for this item.
     * @param createDate creation date for this item.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for id of this item.
     * @return the id of this item.
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for description of this item.
     * @return the description of this item.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter for "is done" state for this item.
     * @return "is done" state for this item.
     */
    public boolean isSold() {
        return sold;
    }

    /**
     * Getter for creation date for this item.
     * @return the creation date for this item.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    public User getUser() {
        if (user instanceof HibernateProxy) {
            Hibernate.initialize(user);
            user = (User) ((HibernateProxy) user)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        if (car instanceof HibernateProxy) {
            Hibernate.initialize(car);
            car = (Car) ((HibernateProxy) car)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", sold=" + sold +
                ", createDate=" + createDate +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
