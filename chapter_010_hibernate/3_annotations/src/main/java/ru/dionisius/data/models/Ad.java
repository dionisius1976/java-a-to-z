package ru.dionisius.data.models;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import ru.dionisius.data.enums.States;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Dionisius on 05.08.2017.
 * POJO.
 * This class stores task's ru.dionisius.ru.dionisius.data
 * and has getters and setters for this ru.dionisius.ru.dionisius.data.
 */
@Entity
@Table(name= "ads")
public class Ad {
    /**
     * Advertisement's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private long id;
    /**
     * Advertisement's login.
     */
    @Column(name = "description", length = 500)
    private String desc;
    /**
     * Advertisement's state.
     */
    @Column(name = "state", length = 500)
    private States state;
    /**
     * The date of advertisement creation.
     */
    private Timestamp createDate;
    /**
     * Master of this advertisement.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * Car that sells in this advertisement.
     */
    @OneToOne
    private Car car;

    /**
     * Default constructor.
     */
    public Ad() {}

    /**
     * Constructor with parameter.
     * @param id advertisement's id.
     */
    public Ad(long id) {
        this.id = id;
    }

    /**
     * Constructor with parameters.
     * @param desc advertisement's description.
     * @param user master of this advertisement.
     * @param car car that sells in this advertisement.
     */
    public Ad(String desc, User user, Car car) {
        this.desc = desc;
        this.state = States.ACTUAL;
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
        this.state = States.SOLD;
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
    public States isSold() {
        return state;
    }

    /**
     * Getter for creation date for this item.
     * @return the creation date for this item.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Getter for user.
     * @return user.
     */
    public User getUser() {
        if (user instanceof HibernateProxy) {
            Hibernate.initialize(user);
            user = (User) ((HibernateProxy) user)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }
        return user;
    }

    /**
     * Setter for user.
     * @param user user that sets.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter for car.
     * @return car.
     */
    public Car getCar() {
        if (car instanceof HibernateProxy) {
            Hibernate.initialize(car);
            car = (Car) ((HibernateProxy) car)
                    .getHibernateLazyInitializer()
                    .getImplementation();
        }
        return car;
    }

    /**
     * Setter for car.
     * @param car car.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", sold=" + state.toString().toLowerCase() +
                ", createDate=" + createDate +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
