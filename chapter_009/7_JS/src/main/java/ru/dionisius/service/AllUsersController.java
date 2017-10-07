package ru.dionisius.service;

import com.google.gson.Gson;
import ru.dionisius.control.DbManager;
import ru.dionisius.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Dionisius on 13.08.2017.
 */
public class AllUsersController extends HttpServlet {

    /**
     * Returns all users.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = DbManager.getInstance().getAllUsers();
        String json = new Gson().toJson(users);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }

    /**
     * Creates new user and adds it to database.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbManager.getInstance().createUser(req.getParameter("name"),
                req.getParameter("login"), req.getParameter("email"),
                req.getParameter("country"), req.getParameter("city"));
    }
}
