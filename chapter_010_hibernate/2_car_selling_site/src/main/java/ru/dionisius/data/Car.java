package ru.dionisius.data;

/**
 * Created by Dionisius on 29.09.2017.
 */
public class Car {
    /**
     * Car's id.
     */
    private long id;
    /**
     * Car's brand.
     */
    private String brand;
    /**
     * Car's model.
     */
    private String model;
    /**
     * Car's transmission type.
     */
    private String transmission;
    /**
     * Car's engine capacity.
     */
    private float engineCapacity;
    /**
     * Car's year of issue.
     */
    private int year;

    public Car() {
    }

    public Car(long id) {
        this.id = id;
    }

    public Car(String brand, String model, String transmission, float engineCapacity, int year) {
        this.brand = brand;
        this.model = model;
        this.transmission = transmission;
        this.engineCapacity = engineCapacity;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
