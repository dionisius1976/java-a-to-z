package ru.dionisius.data.dao;

import org.springframework.stereotype.Repository;
import ru.dionisius.data.models.Ad;
import ru.dionisius.data.models.Car;
import ru.dionisius.data.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dionisius on 14.11.2017.
 */
@Repository
public class FakeDao implements IAdsDao {
    private final Ad ad1 = new Ad("Ad1", 300,
            new User("1", "1", "Ivan", "Poddubnyy", "+79217776655"),
            new Car("Mersedes", "S600", "auto", 6.0f, 2006));
    private final Ad ad2 = new Ad("Ad2", 500,
            new User("2", "2", "Irina", "Ivanova", "+79217776699"),
            new Car("BMW", "M5", "auto", 3.5f, 2016));
    private List<Ad> ads = new ArrayList<>();

    public FakeDao() {
        this.ads.add(ad1);
        this.ads.add(ad2);
        System.out.println("Hi, I'm FakeDao! I'm in context.");
    }

    @Override
    public long addAd(Ad ad) {
        this.ads.add(ad);
        return 0;
    }

    @Override
    public void updateAd(Ad ad) {}

    @Override
    public List<Ad> getAll() {
        return this.ads;
    }

    @Override
    public Ad getAdById(long id) {
        return null;
    }

    @Override
    public void removeAd(long id) {}
}
