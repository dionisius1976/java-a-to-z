package ru.dionisius.service;

import ru.dionisius.data.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dionisius on 14.09.2017.
 */
public class CreateAdController extends HttpServlet {

    /**
     * Creates specified in request advertisement.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Car car = new Car();
        car.setBrand(req.getParameter("brand"));
        car.setModel(req.getParameter("model"));
        car.setTransmission(req.getParameter("transmission"));
        car.setYear(Integer.valueOf(req.getParameter("year")));
        car.setEngineCapacity(Float.valueOf(req.getParameter("capacity")));

        Ad ad = new Ad(req.getParameter("desc"), user, car);
        IDbManager manager = (DbManager)req.getServletContext().getAttribute("dBManager");
        manager.createAdvertisement(ad);
        List<Ad> ads = manager.getAll();
//        resp.sendRedirect(String.format("%s/main.html", req.getContextPath()));
//        req.getRequestDispatcher(String.format("%s/main.html", req.getContextPath())).forward(req,resp);
    }
}
