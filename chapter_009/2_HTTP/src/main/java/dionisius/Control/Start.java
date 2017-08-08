package dionisius.Control;

/**
 * Created by Dionisius on 08.08.2017.
 */
public class Start {
    public static void main(String[] args) {
        IDbManager manager = new DbManager("config.properties");
        manager.createUser("Ivan", "111", "ivan@yandex.ru");
        System.out.println(manager.getUser("Ivan", "111"));
        manager.deleteUser("Ivan", "111");
        System.out.println(manager.getUser("Ivan", "111"));
    }
}
