package ru.dionisius.data;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Dionisius on 05.08.2017.
 * POJO.
 * This class stores task's data
 * and has getters and setters for this data.
 */
@Entity
@Table(name= "ads")
public class Ad {
    /**
     * Advertisement's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    /**
     * Advertisement's login.
     */
    private String desc;
    /**
     * Advertisement's state.
     */
    private boolean sold;
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
                ", sold=" + sold +
                ", createDate=" + createDate +
                ", user=" + user +
                ", car=" + car +
                '}';
    }
}
