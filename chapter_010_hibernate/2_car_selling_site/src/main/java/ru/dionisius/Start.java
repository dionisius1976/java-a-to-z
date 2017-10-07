package ru.dionisius;

import ru.dionisius.data.*;

/**
 * Created by Dionisius on 04.10.2017.
 */
public class Start {
    public static void main(String[] args) {
        IDbManager manager = new DbManager();
        Car car = new Car("Mersedes", "s600", "auto", 6.0f, 2009);
        User user = new User("5", "5", "Sergey", "Petrov", "+79112223344");
        Ad ad = new Ad("My car is the best!", user, car);
        manager.createAdvertisement(ad);
            manager.closeConnection();

    }
}
