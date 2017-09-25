package ru.dionisius.servlets;

import ru.dionisius.control.DbManager;
import ru.dionisius.control.IDbManager;
import ru.dionisius.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dionisius on 13.08.2017.
 */
public class GetUserServlet extends HttpServlet {
    /**
     * Specified dbManager instance.
     */
    private final IDbManager dbManager = new DbManager("config.properties");

    /**
     * Returns user's information.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("name");
        String userLogin = req.getParameter("login");
        User user = this.dbManager.getUser(userName, userLogin);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>");
        if (user != null) {
            writer.append(String.format("<p>%s</p>", user.toString()));
        } else {
            writer.append(String.format("<p>There is no user with name %s and login %s.</p>", userName, userLogin));
        }
        writer.append("<p><a href=" + req.getContextPath() + "/index method='get'>Go to main page.</a></p>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    public void destroy() {
        this.dbManager.disconnectDb();
    }
}
