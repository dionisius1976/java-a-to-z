package ru.dionisius.data.dbtools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    /**
     * SessionFactory instance.
     */
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * SessionFactory getter.
     * @return new sessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
