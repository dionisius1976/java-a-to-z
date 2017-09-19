package dionisius.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dionisius on 14.09.2017.
 * Provides quit for authenticated user.
 */
public class QuitController extends HttpServlet {

    /**
     * Provides quit for authenticated user.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("role");
        resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
    }
}
