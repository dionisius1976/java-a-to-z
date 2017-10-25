package ru.dionisius.data.dbtools;

import org.junit.Assert;
import org.junit.Test;
import ru.dionisius.data.pojo.Ad;
import ru.dionisius.data.pojo.Car;
import ru.dionisius.data.pojo.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Dionisius on 03.10.2017.
 */
public class DbManagerTest {
    /**
     * DbManger instance.
     */
    private static final IDbManager manager = new DbManager();
    /**
     * Car instance for tests.
     */
    private static final Car car1 = new Car("BMW", "X6", "auto", 3.0f, 2015);
    /**
     * Car instance for tests.
     */
    private static final Car car2  = new Car("Toyota", "Camry", "auto", 3.5f, 2016);
    /**
     * Car instance for tests.
     */
    private static final Car car3  = new Car("VAZ", "21099", "manual", 1.5f, 2006);
    /**
     * User instance for tests.
     */
    private static final User user1 = new User("1", "1", "Ivan", "Spiridomov",
            "+79213334455");
    /**
     * User instance for tests.
     */
    private static final User user2 = new User("2", "2", "Stas", "Ogurtsov",
            "+79114543322");
    /**
     * User instance for tests.
     */
    private static final User user3 = new User("3", "3", "Vlad", "Pomidorov",
            "+79213457781");
    /**
     * Advertisement instance for tests.
     */
    private static Ad ad1 = new Ad("Buy my perfect car!", user1, car1);
    /**
     * Advertisement instance for tests.
     */
    private static Ad ad2 = new Ad("Buy my car! Please!", user2, car2);
    /**
     * Advertisement instance for tests.
     */
    private static Ad ad3 = new Ad("Hi!", user3, car3);
    /**
     * Inaccuracy instance for float variable test.
     */
    private static final double DELTA = 1e-15;

    /**
     * Tests createAdvertisement method.
     * @throws Exception if oocurs.
     */
    @Test
    public void createAdvertisement() throws Exception {
        List<Ad>initialList = this.manager.getAll();
        int initialAdsNumber = 0;
        Assert.assertEquals(initialList.size(), initialAdsNumber);
        manager.createAdvertisement(ad1);
        manager.createAdvertisement(ad2);
        List<Ad> expectedList = new ArrayList<>();
        expectedList.add(ad1);
        expectedList.add(ad2);
        List<Ad>resultList = manager.getAll();
        int counter = 0;
        int expectedAdsNumber = 2;
        Assert.assertEquals(resultList.size(), expectedAdsNumber);
        for (Ad ad: resultList) {
            Assert.assertEquals(ad.getDesc(), expectedList.get(counter).getDesc());
            Assert.assertEquals(ad.getCar().getBrand(), expectedList.get(counter).getCar().getBrand());
            Assert.assertEquals(ad.getCar().getModel(), expectedList.get(counter).getCar().getModel());
            Assert.assertEquals(ad.getCar().getEngineCapacity(), expectedList.get(counter).getCar().getEngineCapacity(), DELTA);
            Assert.assertEquals(ad.getCar().getYear(), expectedList.get(counter).getCar().getYear());
            Assert.assertEquals(ad.getCar().getTransmission(), expectedList.get(counter).getCar().getTransmission());
            Assert.assertEquals(ad.getUser().getLogin(), expectedList.get(counter).getUser().getLogin());
            Assert.assertEquals(ad.getUser().getPassword(), expectedList.get(counter).getUser().getPassword());
            Assert.assertEquals(ad.getUser().getName(), expectedList.get(counter).getUser().getName());
            Assert.assertEquals(ad.getUser().getSurname(), expectedList.get(counter).getUser().getSurname());
            Assert.assertEquals(ad.getUser().getTelNumber(), expectedList.get(counter).getUser().getTelNumber());
            ++counter;
        }
        manager.closeConnection();
    }
    /**
     * Tests getAllActual method.
     * @throws Exception if oocurs.
     */
    @Test
    public void getAllActual() throws Exception {
        List<Ad>adList = this.manager.getAll();
        int adsNumber = 0;
        Assert.assertEquals(adList.size(), adsNumber);

        long idAd1 = manager.createAdvertisement(ad1);
        long idAd2 = manager.createAdvertisement(ad2);
        long idAd3 = manager.createAdvertisement(ad3);

        adList = this.manager.getAll();
        adsNumber = 3;
        Assert.assertEquals(adList.size(), adsNumber);


        ad1.setId(idAd1);
        ad2.setId(idAd2);
        ad3.setId(idAd3);
        Set<Ad> soldAds = new HashSet<>();
        soldAds.add(ad2);
        soldAds.add(ad3);
        manager.setSold(soldAds);

        List<Ad> actualList = manager.getActual();

        List<Ad>expectedList = new ArrayList<>();
        expectedList.add(ad1);

        int expectedActualAdsNumber = 1;
        Assert.assertEquals(actualList.size(), expectedActualAdsNumber);

        int counter = 0;
        for (Ad ad: actualList) {
            Assert.assertEquals(ad.getDesc(), expectedList.get(counter).getDesc());
            Assert.assertEquals(ad.getCar().getBrand(), expectedList.get(counter).getCar().getBrand());
            Assert.assertEquals(ad.getCar().getModel(), expectedList.get(counter).getCar().getModel());
            Assert.assertEquals(ad.getCar().getEngineCapacity(), expectedList.get(counter).getCar().getEngineCapacity(), DELTA);
            Assert.assertEquals(ad.getCar().getYear(), expectedList.get(counter).getCar().getYear());
            Assert.assertEquals(ad.getCar().getTransmission(), expectedList.get(counter).getCar().getTransmission());
            Assert.assertEquals(ad.getUser().getLogin(), expectedList.get(counter).getUser().getLogin());
            Assert.assertEquals(ad.getUser().getPassword(), expectedList.get(counter).getUser().getPassword());
            Assert.assertEquals(ad.getUser().getName(), expectedList.get(counter).getUser().getName());
            Assert.assertEquals(ad.getUser().getSurname(), expectedList.get(counter).getUser().getSurname());
            Assert.assertEquals(ad.getUser().getTelNumber(), expectedList.get(counter).getUser().getTelNumber());
            ++counter;
        }
        manager.closeConnection();
    }
    /**
     * Tests getAll method.
     * @throws Exception if oocurs.
     */
    @Test
    public void getAll() throws Exception {
        this.createAdvertisement();
    }
    /**
     * Tests setSold method.
     * @throws Exception if oocurs.
     */
    @Test
    public void setSold() throws Exception {
        this.getAllActual();
    }
    /**
     * Tests createUser method.
     * @throws Exception if oocurs.
     */
    @Test
    public void whenCreateUserThenUserIsCreated() throws Exception {
        User expectedUser = user1;
        manager.createUser(user1);
        User resultUser = manager.getUserByLoginAndPassword("1", "1");
        Assert.assertEquals(expectedUser.getLogin(), resultUser.getLogin());
        Assert.assertEquals(expectedUser.getPassword(), resultUser.getPassword());
        Assert.assertEquals(expectedUser.getName(), resultUser.getName());
        Assert.assertEquals(expectedUser.getSurname(), resultUser.getSurname());
        Assert.assertEquals(expectedUser.getTelNumber(), resultUser.getTelNumber());
    }
    /**
     * Tests getUserByLoginAndPassword method.
     * @throws Exception if oocurs.
     */
    @Test
    public void whenGetUserByLoginAndPasswordThenExpectedUserIsInDatabase() throws Exception {
        this.whenCreateUserThenUserIsCreated();
    }


}