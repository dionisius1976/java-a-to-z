package ru.dionisius.servlets;

import com.google.gson.Gson;
import ru.dionisius.control.DbManager;
import ru.dionisius.models.Address;
import ru.dionisius.models.MusicType;
import ru.dionisius.models.Role;
import ru.dionisius.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dionisius on 14.09.2017.
 */
public class CreateUserController extends HttpServlet {

    private final String[] musicTypesArray = {"ROCK", "RAP", "SOUL", "DISCO", "PUNK", "JAZZ"};
    private final List<String> musicTypes = Arrays.asList(this.musicTypesArray);

    /**
     * Returns all users.
     * @param req client's request instance.
     * @param resp response instance for client.
     * @throws ServletException if ServletException occurs.
     * @throws IOException if IOException occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("role") != null) {
            List<User> users = DbManager.getInstance().getAllUsers();
            String json = new Gson().toJson(users);
            resp.setContentType("text/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(json);
            writer.flush();
        } else {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
            return;
        }
    }

    /**
     * Creates specified in request user.
     * @param req user's request.
     * @param resp response for user.
     * @throws ServletException if occurs.
     * @throws IOException if occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address userAddress = new Address(req.getParameter("zip_code"), req.getParameter("country"),
                                        req.getParameter("city"), req.getParameter("street"),
                                        req.getParameter("house"), req.getParameter("flat"));
        List<MusicType> userMusicTypeList = new LinkedList<>();
        Map<String, String[]> parameters = req.getParameterMap();
        String key;
        for(Map.Entry entry: parameters.entrySet()) {
            key = (String)entry.getKey();
            if (this.musicTypes.contains(key.toUpperCase())) {
                userMusicTypeList.add(MusicType.valueOf(key.toUpperCase()));
            }
        }
        DbManager.getInstance().createUser(req.getParameter("name"), req.getParameter("surname"),
                req.getParameter("login"), req.getParameter("password"), userAddress,
                Role.valueOf(req.getParameter("role")), userMusicTypeList);
    }
}
