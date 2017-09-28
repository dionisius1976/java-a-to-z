package ru.dionisius.controller;

import ru.dionisius.control.DbManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dionisius on 16.09.2017.
 * Deletes specified in request user.
 */
public class DeleteUserController extends HttpServlet {
//    /**
//     * Database manager instance.
//     */
//    private final IDbManager manager = DbManager.getInstance();

    /**
     * Deletes specified in request user.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbManager.getInstance().deleteUser(req.getParameter("id"));
    }
}
