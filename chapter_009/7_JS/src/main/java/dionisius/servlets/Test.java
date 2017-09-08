package dionisius.servlets;

import com.google.gson.Gson;
import dionisius.control.DbManager;
import dionisius.models.User;

import java.util.List;

/**
 * Created by Dionisius on 08.09.2017.
 */
public class Test {
    public static void main(String[] args) {
        DbManager.getInstance().createUser("Ivan", "111", "111@111", "Russia",
                "Smolensk");
        List<User> users = DbManager.getInstance().getAllUsers();
        for(User user: users) {
            System.out.println(user);
        }
        String json = new Gson().toJson(users);
        System.out.println(json);
    }
}
