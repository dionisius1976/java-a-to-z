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
public class EditUserServlet extends HttpServlet {

    /**
     * Edits user's login and email.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        DbManager.getInstance().editUser(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("newName"), req.getParameter("newLogin"));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
