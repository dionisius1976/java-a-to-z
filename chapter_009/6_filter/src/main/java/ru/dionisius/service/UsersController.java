package ru.dionisius.service;

import ru.dionisius.control.DbManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dionisius on 13.08.2017.
 */
public class UsersController extends HttpServlet {

    /**
     * Creates new user.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", DbManager.getInstance().getAllUsers());
        req.getRequestDispatcher("/WEB-INF/ru.dionisius.data/UsersView.jsp").forward(req, resp);
    }

    /**
     * Creates new user.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        DbManager.getInstance().createUser(req.getParameter("name"),
                req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
