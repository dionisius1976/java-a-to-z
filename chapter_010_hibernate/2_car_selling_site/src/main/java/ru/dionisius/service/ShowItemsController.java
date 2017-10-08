package ru.dionisius.service;

import com.google.gson.Gson;
import ru.dionisius.data.Ad;
import ru.dionisius.data.DbManager;
import ru.dionisius.data.IDbManager;

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
     * Returns all advertisements in json type.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDbManager manager = (DbManager)req.getServletContext().getAttribute("dBManager");
        List<Ad> ads = manager.getAll();
        String jsonItems = new Gson().toJson(ads);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonItems);
        writer.flush();
    }

    /**
     * Returns all actual advertisements in json type.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDbManager manager = (DbManager)req.getServletContext().getAttribute("dBManager");
        List<Ad> ads = manager.getActual();
        String jsonItems = new Gson().toJson(ads);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonItems);
        writer.flush();
    }
}
