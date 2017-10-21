package ru.dionisius.service.servlets;

import ru.dionisius.data.pojo.Ad;
import ru.dionisius.DbManager;
import ru.dionisius.IDbManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Dionisius on 03.10.2017.
 */
public class SetSoldController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDbManager manager = (DbManager)req.getServletContext().getAttribute("dBManager");
        Set<Ad> ads = (Set<Ad>)req.getAttribute("ads");
        manager.setSold(ads);
    }
}
