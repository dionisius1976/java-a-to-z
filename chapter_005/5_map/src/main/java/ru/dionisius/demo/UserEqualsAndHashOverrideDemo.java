package ru.dionisius.demo;

import ru.dionisius.users.AUser;
import ru.dionisius.users.UserHashAndEqualsOverride;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dionisius on 16.02.2017.
 * Expose the result of insert two equal objects as keys to Hashmap.
 * With override hash() and equals() methods only one last putted
 * object is in this map.
 */
public class UserEqualsAndHashOverrideDemo {
    public static void main(String[] args) {

        Map<AUser, String> map = new HashMap<>();
        Calendar birthday = Calendar.getInstance();
        birthday.set(1970, 1, 20);

        AUser user1 = new UserHashAndEqualsOverride("Ivan", 2, birthday);
        AUser user2 = new UserHashAndEqualsOverride("Ivan", 2, birthday);

        map.put(user1, "first");
        map.put(user2, "second");

        System.out.println(map);
        System.out.println(map.get(user1));
        System.out.println(map.get(user2));
        System.out.println(map.containsKey(user1));
        System.out.println(map.containsKey(user2));
    }
}
