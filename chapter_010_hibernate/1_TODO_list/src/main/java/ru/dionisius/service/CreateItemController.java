package ru.dionisius.service;

import ru.dionisius.data.DbManager;
import ru.dionisius.data.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by Dionisius on 14.09.2017.
 */
public class CreateItemController extends HttpServlet {

    /**
     * Creates specified in request user.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean done = false;
        if(req.getParameter("done") != null) {
            done = true;
        }
        Item item = new Item();
        item.setDesc(req.getParameter("desc"));
        item.setDone(done);
        item.setCreateDate(new Timestamp(System.currentTimeMillis()));
        new DbManager().createItem(item);
    }
}
