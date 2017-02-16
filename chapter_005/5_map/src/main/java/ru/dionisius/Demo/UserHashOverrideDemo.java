package ru.dionisius.Demo;

import ru.dionisius.Users.AUser;
import ru.dionisius.Users.UserHashOverride;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dionisius on 16.02.2017.
 * Expose the result of insert two equal objects as keys to Hashmap.
 * Without override hash() method these equal objects
 * are in this map.
 */
public class UserHashOverrideDemo {

    public static void main(String[] args) {

        Map<AUser, String> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        birthday.set(1970, 1, 20);

        AUser user1 = new UserHashOverride("Ivan", 2, birthday);
        AUser user2 = new UserHashOverride("Ivan", 2, birthday);

        map.put(user1, "first");
        map.put(user2, "second");

        System.out.println(map);
    }
}
