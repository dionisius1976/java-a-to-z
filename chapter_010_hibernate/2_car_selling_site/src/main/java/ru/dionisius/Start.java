package ru.dionisius;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.dionisius.data.*;

/**
 * Created by Dionisius on 04.10.2017.
 */
public class Start {

    private final static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public static long getIdByUser(User user) {
        long id = 0;
        Query query = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            query = session.createQuery("from User where login =:log AND password =:pass");
            query.setParameter("log", user.getLogin());
            query.setParameter("pass", user.getPassword());
            User hiberUser = (User)query.list().get(0);
            id = hiberUser.getId();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
        }
        return id;
    }
    public static void main(String[] args) {

//        IDbManager manager = new DbManager();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            Car car = new Car("Mersedes", "s600", "auto", 6.0f, 2009);
            User user = new User("5", "5", "Sergey", "Petrov", "+79112223344");
            tx = session.beginTransaction();
            long carId = (long) session.save(car);
            long userId = getIdByUser(user);
            if (userId == 0) {
                userId = (long) session.save(user);
            }
            Ad ad = new Ad("uuu", new User(userId), new Car(carId));
            long adId = (long) session.save(ad);
            tx.commit();
            System.out.println(session.get(Ad.class, adId));
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
        } finally {
            session.close();
            factory.close();
        }
    }
}
