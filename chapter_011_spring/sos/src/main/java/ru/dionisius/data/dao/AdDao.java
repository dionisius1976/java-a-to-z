package ru.dionisius.data.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.dionisius.data.models.Ad;
import ru.dionisius.data.models.Car;

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
     * SessionFactory instance.
     */
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public AdDao() {
        System.out.println("Hi! I'm Real DAO!");
    }

    @Override
    public long addAd(Ad ad) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        long id = 0;
        try {
            tx = session.beginTransaction();
            id = (Long)session.save(ad);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return id;
    }

    @Override
    public void updateAd(Ad ad) {
        this.removeAd(ad.getId());
        this.addAd(ad);
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
//    @Transactional
    public void removeAd(long id) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        Long carId = this.getCarId(id);
        try {
            tx = session.beginTransaction();
            session.delete(new Car(carId));
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    private long getCarId(final long adId) {
        Session session = this.sessionFactory.openSession();
        long carId = 0;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            carId = session.load(Ad.class, new Long(adId)).getCar().getId();
            System.out.println("carId = "+carId);
            session.delete(new Car(carId));
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return carId;
    }

    @Override
    protected void finalize() throws Throwable {
        this.sessionFactory.close();
        super.finalize();
    }
}
