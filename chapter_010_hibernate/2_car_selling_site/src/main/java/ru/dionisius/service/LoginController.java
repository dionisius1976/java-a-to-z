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
import java.io.PrintWriter;

/**
 * Created by Dionisius on 06.10.2017.
 */
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDbManager manager = (DbManager)req.getServletContext().getAttribute("dBManager");
        User user = manager.getUserByLoginAndPassword(req.getParameter("login"),
                req.getParameter("password"));
        HttpSession session = req.getSession();
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("logged", true);
        } else {
            session.setAttribute("logged", false);
        }
//        RequestDispatcher dispatcher = req.getRequestDispatcher("main.html");
//        dispatcher.forward(req,resp);
//        String path = String.format("%s/main.html", req.getContextPath());
//        resp.sendRedirect(path);
//        req.getRequestDispatcher(String.format("%s/main.html", req.getContextPath())).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String response = null;
        User user = (User)session.getAttribute("user");
        if (user != null) {
            response = String.format("Hi, %s %s.", user.getName(), user.getSurname());
        } else {
            response = "Hi, guest.";
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(response);
        writer.flush();
    }
}
