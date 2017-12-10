package ru.dionisius.data.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dionisius on 29.09.2017.
 */
@Entity
@Table(name = "cars")
public class Car {
    /**
     * Car's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Car's brand.
     */
    @Column(name = "brand")
    private String brand;
    /**
     * Car's model.
     */
    @Column(name = "model")
    private String model;
    /**
     * Car's transmission type.
     */
    @Column(name = "transmission")
    private String transmission;
    /**
     * Car's engine capacity.
     */
    @Column(name = "engine")
    private float engineCapacity;
    /**
     * Car's year of issue.
     */
    @Column(name = "year")
    private int year;
    /**
     *
     */
    @OneToOne (mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Ad ad;

    /**
     * Default constructor.
     */
    public Car() {}

    /**
     * Constructor with parameter.
     * @param id car's id.
     */
    public Car(long id) {
        this.id = id;
    }

    /**
     * Constructor with parameters.
     * @param brand car's brand.
     * @param model car's model.
     * @param transmission car's transmission.
     * @param engineCapacity car's engine capacity.
     * @param year car's issue year.
     */
    public Car(String brand, String model, String transmission, float engineCapacity, int year) {
        this.brand = brand;
        this.model = model;
        this.transmission = transmission;
        this.engineCapacity = engineCapacity;
        this.year = year;
    }

    /**
     * Car's id getter.
     * @return car's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Car's id setter.
     * @param id car's id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Car's brand getter.
     * @return car's brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Car's brand setter.
     * @param brand car's brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Car's model getter.
     * @return car's model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Car's model setter.
     * @param model car;s model.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Car;s transmission getter.
     * @return car's transmission.
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * Car's transmission setter.
     * @param transmission car's transmission.
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Car's engine capacity getter.
     * @return car's engine capacity.
     */
    public float getEngineCapacity() {
        return engineCapacity;
    }

    /**
     * Car's engine capacity setter.
     * @param engineCapacity car's engine capacity.
     */
    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    /**
     * Car's issue year getter.
     * @return car's issue year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Car's issue year setter.
     * @param year car's issue year.
     */
    public void setYear(int year) {
        this.year = year;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", transmission='" + transmission + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", year=" + year +
                '}';
    }
}
