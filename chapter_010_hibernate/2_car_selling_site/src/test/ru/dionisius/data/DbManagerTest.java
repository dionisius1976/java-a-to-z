package ru.dionisius.data;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dionisius on 03.10.2017.
 */
public class DbManagerTest {

    private static IDbManager manager;
    private static Car car1;
    private static Car car2;
    private static User user;
    private static Ad ad1;
    private static Ad ad2;
    private static final double DELTA = 1e-15;

    @BeforeClass
    public static void setUp() throws Exception {
        manager = new DbManager();
        car1 = new Car("BMW", "X6", "auto", 3.0f, 2015);
        car2 = new Car("Toyota", "Camry", "auto", 3.5f, 2016);
        user = new User("1", "1", "Ivan", "Spiridomov", "+79213334455");
        ad1 = new Ad("Buy my perfect car!", user, car1);
        ad2 = new Ad("Buy my perfect car! Please!", user, car2);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        manager.closeConnection();
    }

    @Test
    public void createAdvertisement() throws Exception {
        this.manager.createAdvertisement(ad1);
        this.manager.createAdvertisement(ad2);
        List<Ad>expextedList = new ArrayList<>();
        expextedList.add(ad1);
        expextedList.add(ad2);
        List<Ad>resultList = this.manager.getAll();
        int counter = 0;
        for (Ad ad: resultList) {
            Assert.assertEquals(ad.getDesc(), expextedList.get(counter).getDesc());
            Assert.assertEquals(ad.getCar().getBrand(), expextedList.get(counter).getCar().getBrand());
            Assert.assertEquals(ad.getCar().getModel(), expextedList.get(counter).getCar().getModel());
            Assert.assertEquals(ad.getCar().getEngineCapacity(), expextedList.get(counter).getCar().getEngineCapacity(), DELTA);
            Assert.assertEquals(ad.getCar().getYear(), expextedList.get(counter).getCar().getYear());
            Assert.assertEquals(ad.getCar().getTransmission(), expextedList.get(counter).getCar().getTransmission());
            ++counter;
        }
    }

    @Test
    public void getAll() throws Exception {
        List<Ad>expextedList = new LinkedList<>();
        expextedList.add(ad1);
        expextedList.add(ad2);
        List<Ad>resultList = this.manager.getAll();
    }

    @Test
    public void getAllPerformed() throws Exception {
    }

    @Test
    public void setSold() throws Exception {
    }

}