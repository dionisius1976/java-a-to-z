package ru.dionisius.service.servlets;

import com.google.gson.Gson;
import ru.dionisius.DbManager;
import ru.dionisius.IDbManager;
import ru.dionisius.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dionisius on 08.10.2017.
 * Servlet.
 * Adds specified in form user to database.
 * Returns String "ok" if specified user is added or
 * nothing if user with specified in form
 * login and password is already exists.
 */
public class CreateUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result = null;
        String userLogin = req.getParameter("login");
        String userPassword =  req.getParameter("password");
        IDbManager manager = (DbManager)req.getServletContext().getAttribute("dBManager");
        if (manager.getUserByLoginAndPassword(userLogin, userPassword) == null) {
            HttpSession session = req.getSession();
            manager.createUser(new User(userLogin, userPassword, req.getParameter("name"),
                    req.getParameter("surname"), req.getParameter("tel_number")));
            User user = manager.getUserByLoginAndPassword(userLogin, userPassword);
            session.setAttribute("client", "user");
            session.setAttribute("user", user);
            result = "ok";
        }
        String jsonItems = new Gson().toJson(result);
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonItems);
        writer.flush();
    }
}
