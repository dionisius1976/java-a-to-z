package ru.dionisius.data.models;

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
