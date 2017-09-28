package ru.dionisius.view;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.dionisius.model.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Dionisius on 16.09.2017.
 * Updates specified user.
 */
public class ShowItemsController extends HttpServlet {
    /**
     * Returns all items.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        factory.close();
        String jsonItems = new Gson().toJson(items);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonItems);
        writer.flush();
    }

    /**
     * Returns all performed items.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item where done =:code");
        query.setParameter("code", true);
        List<Item> items = query.list();
        session.getTransaction().commit();
        session.close();
        factory.close();
        String jsonItems = new Gson().toJson(items);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonItems);
        writer.flush();
    }
}
