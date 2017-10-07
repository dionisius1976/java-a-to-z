package ru.dionisius.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Dionisius on 02.10.2017.
 */
public class DbManager implements IDbManager {

    /**
     * Hibernate session factory instance.
     */
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @Override
    public void createItem(final Item newItem) {
        Session session = this.factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDesc(newItem.getDesc());
        item.setDone(newItem.isDone());
        item.setCreateDate(newItem.getCreateDate());
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = null;
        Session session = this.factory.openSession();
        session.beginTransaction();
        items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    @Override
    public List<Item> getAllPerformed() {
        List<Item> items = null;
        Query query = null;
        Session session = this.factory.openSession();
        session.beginTransaction();
        query = session.createQuery("from Item where done =:code");
        query.setParameter("code", true);
        items = query.list();
        session.getTransaction().commit();
        session.close();
        return items;
    }

    @Override
    protected void finalize() throws Throwable {
        this.factory.close();
    }
}
