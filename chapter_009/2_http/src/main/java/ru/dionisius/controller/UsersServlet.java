package ru.dionisius.controller;

import ru.dionisius.control.DbManager;
import ru.dionisius.control.IDbManager;
import ru.dionisius.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dionisius on 03.08.2017.
 * This servlet works with specified in
 * config.properties file database.
 */
public class UsersServlet extends HttpServlet {
    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);
    /**
     * Specified dbManager instance.
     */
    private final IDbManager dbManager = new DbManager("config.properties");

    /**
     * Returns user specified by name and login.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        User user = this.dbManager.getUser(userName, userLogin);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (user != null) {
            writer.append(user.toString());
        } else {
            writer.append(String.format("There is no user with name %s and login %s.", userName, userLogin));
        }
        writer.flush();
    }

    /**
     * Edits user's information.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String newUserLogin = req.getParameter("newLogin");
        String newUserEmail = req.getParameter("newEmail");
        this.dbManager.editUser(userName, userLogin, newUserLogin, newUserEmail);
    }

    /**
     * Creates new user.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        String userEmail = req.getParameter("email");
        this.dbManager.createUser(userName, userLogin, userEmail);
    }

    /**
     * Deletes user specified by name and login.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        this.dbManager.deleteUser(userName, userLogin);
    }

    @Override
    public void destroy() {
        this.dbManager.disconnectDb();
    }
}
