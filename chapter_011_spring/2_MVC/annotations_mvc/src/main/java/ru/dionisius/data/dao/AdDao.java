package ru.dionisius.data.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.dionisius.data.models.Ad;

import java.util.List;

/**
 * Created by Dionisius on 10.11.2017.
 */
@Repository
public class AdDao implements IAdsDao {
    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AdDao.class);
    /**
     *
     */
    private SessionFactory sessionFactory;

    /**
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public long addAd(Ad ad) {
        return (long) this.sessionFactory.openSession().save(ad);
    }

    @Override
    public void updateAd(Ad ad) {
        this.sessionFactory.openSession().update(ad);
    }

    @Override
    public List<Ad> getAll() {
        return this.sessionFactory.openSession().createQuery("FROM Ad").list();
    }

    @Override
    public Ad getAdById(long id) {
        return (Ad) this.sessionFactory.openSession().load(Ad.class, new Long(id));
    }

    @Override
    public void removeAd(long id) {
        this.sessionFactory.openSession().delete(new Ad(id));
    }
}
