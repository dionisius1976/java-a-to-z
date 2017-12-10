package ru.dionisius.data.dbtools;

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
public class DbManager implements ru.dionisius.data.dbtools.IDbManager {

    /**
     * Hibernate session factory instance.
     */
    private final SessionFactory factory = HibernateUtil.getSessionFactory();
    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbManager.class);

    @Override
    public long createAdvertisement(final Ad newAd) {
        long adId = 0;
        Car car = null;
        long userId = 0;
        long carId = 0;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
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

    public List<Ad> getAdsByUser(User user) {
        List<Ad> ads = null;
        User dbUser = this.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        Query query = null;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            query = session.createQuery("FROM Ad WHERE user =:code");


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
    public List<Ad> getAll() {
        List<Ad> ads = null;
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ads = session.createQuery("FROM Ad").list();
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
            query = session.createQuery("FROM Ad WHERE sold =:code");
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
                query = session.createQuery("UPDATE Ad SET sold =:code WHERE id =:idCode");
                query.setParameter("code", true);
                query.setParameter("idCode", ad.getId());
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
            query = session.createQuery("FROM User WHERE login =:log AND password =:pass");
            query.setParameter("log", login);
            query.setParameter("pass", password);
            List<User> userList = query.list();
            if (userList != null) {
                user = userList.get(0);
            }
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
        User persistUser = this.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if (persistUser != null) {
            userId = persistUser.getId();
        } else {
            Session session = this.factory.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                userId = (long) session.save(user);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                LOG.error(e.getMessage(), e);
            } finally {
                session.close();
            }
        }
        return userId;
    }

    @Override
    public void closeSessionFactory() {
        this.factory.close();
    }

    public void clearAll() {
        Session session = this.factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete Ad ");
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

}
