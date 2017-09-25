package ru.dionisius.models;

/**
 * Created by Dionisius on 09.09.2017.
 */
public class Address {
    private final String zip_code;
    private final String country;
    private final String city;
    private final String street;
    private final String house;
    private final String flat;

    public Address(final String zip_code, final String country, final String city, final String street,
                   final String house, final String flat) {
        this.zip_code = zip_code;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getFlat() {
        return flat;
    }

    @Override
    public String toString() {
        return String.format("zip_code: %s, country: %s, city: %s, street: %s, house: %s, flat: %s",
                this.zip_code, this.country, this.city, this.street, this.house, this.flat);
    }
}

