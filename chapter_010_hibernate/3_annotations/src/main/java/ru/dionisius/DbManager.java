package ru.dionisius;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dionisius.data.models.Ad;
import ru.dionisius.data.models.Car;
import ru.dionisius.data.models.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Dionisius on 02.10.2017.
 */
public class DbManager implements IDbManager {

    /**
     * Hibernate session factory instance.
     */
    private SessionFactory factory = HibernateUtil.getSessionFactory();
    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbManager.class);

    @Override
    public long createAdvertisement(final Ad newAd) {
        long adId = 0;
        Car car = null;
        User user = null;
        long userId = 0;
        long carId = 0;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            user = newAd.getUser();
            car = newAd.getCar();
            tx = session.beginTransaction();
            carId = (long) session.save(car);
            userId = this.createUser(newAd.getUser());
            Ad ad = new Ad(newAd.getDesc(), new User(userId), new Car(carId));
            adId = (long) session.save(ad);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return adId;
    }

    @Override
    public List<Ad> getAll() {
        List<Ad> ads = null;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ads = session.createQuery("from Ad").list();
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return ads;
    }

    @Override
    public List<Ad> getActual() {
        List<Ad> ads = null;
        Query query = null;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            query = session.createQuery("from Ad where sold =:code");
            query.setParameter("code", false);
            ads = query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return ads;
    }

    @Override
    public void setSold(final Set<Ad> ads) {
        Query query = null;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for(Ad ad: ads) {
                query = session.createQuery("update Ad set sold = :code");
                query.setParameter("code", "true");
                query.executeUpdate();
            }
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }


    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        List<Ad> users = null;
        Query query = null;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            query = session.createQuery("from User where login =:log AND password =:pass");
            query.setParameter("log", login);
            query.setParameter("pass", password);
            user = (User)query.list().get(0);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public long createUser(User user) {
        long userId = 0;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            userId = (long) session.save(user);
            tx.commit();
        } catch(Exception e){
            if (tx != null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally{
            session.close();
        }
        return userId;
    }

    @Override
    public void closeConnection() {
        this.factory.close();
    }

}
