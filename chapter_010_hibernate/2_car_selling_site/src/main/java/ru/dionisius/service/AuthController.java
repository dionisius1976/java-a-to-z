package ru.dionisius.service;

import ru.dionisius.data.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dionisius on 07.10.2017.
 * Defines client's role.
 */
public class AuthController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String response = null;
        User user = (User)session.getAttribute("user");
        if (user != null) {
            response = String.format("%s %s", user.getName(), user.getSurname());
        } else {
            response = "guest";
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(response);
        writer.flush();
    }
}
