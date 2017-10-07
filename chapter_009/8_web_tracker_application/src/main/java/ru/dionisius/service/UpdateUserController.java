package ru.dionisius.service;

import ru.dionisius.control.DbManager;
import ru.dionisius.model.Address;
import ru.dionisius.model.MusicType;
import ru.dionisius.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dionisius on 16.09.2017.
 * Updates specified user.
 */
public class UpdateUserController extends HttpServlet {
//    /**
//     * Database manager instance.
//     */
//    private final IDbManager manager = DbManager.getInstance();

    /**
     * Updates specified user.
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
        String[] musicTypes = {"ROCK", "RAP", "SOUL", "DISCO", "PUNK", "JAZZ"};
        Map<String, String[]> parameters = req.getParameterMap();
        String key;
        for(Map.Entry entry: parameters.entrySet()) {
            key = (String)entry.getKey();
            if (Arrays.asList(musicTypes).contains(key.toUpperCase())) {
                userMusicTypeList.add(MusicType.valueOf(key.toUpperCase()));
            }
        }
        DbManager.getInstance().updateUser(req.getParameter("id"), req.getParameter("name"),
                req.getParameter("surname"), req.getParameter("login"),
                req.getParameter("password"), userAddress, Role.valueOf(req.getParameter("role")),
                userMusicTypeList);
    }
}
