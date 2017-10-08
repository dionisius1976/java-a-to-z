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
        session.removeAttribute("client");
        session.removeAttribute("user");
        req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req,resp);
    }
}
