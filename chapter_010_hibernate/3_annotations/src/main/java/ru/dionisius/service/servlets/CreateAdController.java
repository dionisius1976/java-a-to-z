package ru.dionisius.service.servlets;

import ru.dionisius.DbManager;
import ru.dionisius.IDbManager;
import ru.dionisius.data.pojo.Ad;
import ru.dionisius.data.pojo.Car;
import ru.dionisius.data.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dionisius on 14.09.2017.
 * Inserts specified in request advertisement in database.
 */
public class CreateAdController extends HttpServlet {

    /**
     * Inserts specified in request advertisement in database.
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
    }
}
