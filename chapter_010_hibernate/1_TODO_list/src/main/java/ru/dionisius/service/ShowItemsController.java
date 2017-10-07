package ru.dionisius.service;

import com.google.gson.Gson;
import ru.dionisius.data.DbManager;
import ru.dionisius.data.IDbManager;
import ru.dionisius.data.Item;

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
     * Database manager instance.
     */
    IDbManager manager = new DbManager();
    /**
     * Returns all items.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = this.manager.getAll();
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
        List<Item> items = this.manager.getAllPerformed();
        String jsonItems = new Gson().toJson(items);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonItems);
        writer.flush();
    }
}
