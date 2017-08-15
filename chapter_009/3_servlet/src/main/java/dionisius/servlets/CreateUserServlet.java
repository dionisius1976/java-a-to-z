package dionisius.servlets;

import dionisius.control.DbManager;
import dionisius.control.IDbManager;
import dionisius.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dionisius on 13.08.2017.
 */
public class CreateUserServlet extends HttpServlet {
    /**
     * Specified dbManager instance.
     */
    private final IDbManager dbManager = new DbManager("config.properties");

    /**
     * Creates new user.
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
        String userEmail = req.getParameter("email");
        this.dbManager.createUser(userName, userLogin, userEmail);
        User user = this.dbManager.getUser(userName, userLogin);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (user != null) {
            writer.append(String.format("User %s is created.", user.toString()));
        } else {
            writer.append(String.format("User with name %s and login %s is not created.",  userName, userLogin));
        }
        writer.flush();
    }

    @Override
    public void destroy() {
        this.dbManager.disconnectDb();
    }

}
