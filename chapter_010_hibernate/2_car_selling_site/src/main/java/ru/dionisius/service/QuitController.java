package ru.dionisius.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
      HttpSession session = req.getSession();
        req.getSession().removeAttribute("logged");
        req.getSession().removeAttribute("user");
//      session.invalidate();
// req.getSession().removeAttribute("user");
//        req.getSession().removeAttribute("logged");
//        resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
        req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req,resp);
    }
}
