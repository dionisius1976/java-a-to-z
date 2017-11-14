package ru.dionisius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dionisius.data.dao.FakeDAO;
import ru.dionisius.data.dao.IAdsDao;
import ru.dionisius.data.models.Ad;

import java.util.List;

/**
 * Created by Dionisius on 14.11.2017.
 */
@Service
public class AdService implements IAdService {


    @Autowired
//    @Qualifier(value = "FakeDAO")
//    IAdsDao AdDao;
    FakeDAO AdDao;
//    @Autowired
//    @Qualifier(value = "AdDAO")
//    public void setAdDao(IAdsDao adDao) {
//        this.AdDao = adDao;
//    }

    @Override
    @Transactional
    public long addAd(Ad ad) {
        return this.AdDao.addAd(ad);
    }

    @Override
    @Transactional
    public void updateAd(Ad ad) {
        this.AdDao.updateAd(ad);
    }

    @Override
    @Transactional
    public List<Ad> getAll() {
        return this.AdDao.getAll();
    }

    @Override
    @Transactional
    public Ad getAdById(long id) {
        return this.AdDao.getAdById(id);
    }

    @Override
    @Transactional
    public void removeAd(long id) {
        this.AdDao.getAdById(id);
    }
}
