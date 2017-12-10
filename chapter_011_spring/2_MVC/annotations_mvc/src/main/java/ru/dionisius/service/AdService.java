package ru.dionisius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dionisius.data.dao.FakeDao;
import ru.dionisius.data.dao.IAdsDao;
import ru.dionisius.data.models.Ad;

import java.util.List;

/**
 * Created by Dionisius on 14.11.2017.
 */
@Service
public class AdService implements IAdService {
    @Autowired
//    @Qualifier("fakeDao")
    private IAdsDao dao;
//    private FakeDao dao;

    @Override
//    @Transactional
    public long addAd(Ad ad) {
        return this.dao.addAd(ad);
    }

    @Override
//    @Transactional
    public void updateAd(Ad ad) {
        this.dao.updateAd(ad);
    }

    @Override
//    @Transactional
    public List<Ad> getAll() {
        return this.dao.getAll();
    }

    @Override
    @Transactional
    public Ad getAdById(long id) {
        return this.dao.getAdById(id);
    }

    @Override
//    @Transactional
    public void removeAd(long id) {
        this.dao.getAdById(id);
    }
}
