package ru.dionisius.service;

import ru.dionisius.data.DbManager;
import ru.dionisius.data.IDbManager;
import ru.dionisius.data.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dionisius on 06.10.2017.
 * Servlet.
 * Defines client's role and logs client in.
 */
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        IDbManager manager = (DbManager)req.getServletContext().getAttribute("dBManager");
        User user = manager.getUserByLoginAndPassword(login, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("client", "user");
        } else {
            session.setAttribute("client", "unregistered");
        }
    }
}
