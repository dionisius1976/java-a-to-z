package ru.dionisius.service;

import com.google.gson.Gson;
import ru.dionisius.control.DbManager;
import ru.dionisius.model.Address;
import ru.dionisius.model.MusicType;
import ru.dionisius.model.Role;
import ru.dionisius.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dionisius on 14.09.2017.
 */
public class LoginController extends HttpServlet {

    /**
     * Database manager instance.
     */
    private final DbManager manager = DbManager.getInstance();
    /**
     * Flag for checking if initial users are created.
     */
    private boolean initialUsersAreCreated = false;

    /**
     * Creates initial users if they didn't been created.
     * Checks if logging user exists and if exists
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!this.initialUsersAreCreated) {
            this.createInitialUsers();
            this.initialUsersAreCreated = true;
        }
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User user = this.manager.getUserIdByLoginAndPassword(req.getParameter("login"),
                req.getParameter("password"));
        if (user != null) {
            HttpSession session = req.getSession();
            if (user.getRole().equals(Role.USER)){
                writer.append(new Gson().toJson("user"));
                session.setAttribute("role", "user");
            } else if (user.getRole().equals(Role.MANDATOR)) {
                writer.append(new Gson().toJson("mandator"));
                session.setAttribute("role", "mandator");
            } else {
                writer.append(new Gson().toJson("admin"));
                session.setAttribute("role", "admin");
            }
        } else {
            writer.append(new Gson().toJson("Wrong user! Try again."));
        }
        writer.flush();
    }

    /**
     * Creates initial users for application working ability tests.
     */
    private void createInitialUsers() {
        Address user1Address = new Address("191000", "Russia",
                "Moskow", "Pushkina", "15", "176");
        Address user2Address = new Address("185678", "Belorus",
                "Minsk", "Lukashenko", "17/7", "88");
        Address user3Address = new Address("185897", "Russia",
                "Smolensk", "Putina", "1", "8");
        List<MusicType> user1MusicTypeList = new LinkedList<>();
        List<MusicType> user2MusicTypeList = new LinkedList<>();
        List<MusicType> user3MusicTypeList = new LinkedList<>();
        user1MusicTypeList.add(MusicType.JAZZ);
        user1MusicTypeList.add(MusicType.ROCK);
        user1MusicTypeList.add(MusicType.RAP);
        user2MusicTypeList.add(MusicType.DISCO);
        user2MusicTypeList.add(MusicType.SOUL);
        user3MusicTypeList.add(MusicType.RAP);
        this.manager.createUser("Ivan", "Petrov", "1",
                "1", user1Address, Role.ADMIN, user1MusicTypeList);
        this.manager.createUser("Sergey", "Ivanov", "2",
                "2", user2Address, Role.MANDATOR, user2MusicTypeList);
        this.manager.createUser("Yuriy", "Nikitin", "3",
                "3", user3Address, Role.USER, user3MusicTypeList);
    }
}
