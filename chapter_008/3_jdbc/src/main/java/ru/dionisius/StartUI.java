package ru.dionisius;

import ru.dionisius.controllers.input.Input;
import ru.dionisius.controllers.MenuTracker;
import ru.dionisius.controllers.Tracker;
import ru.dionisius.controllers.input.ValidateInput;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.sql.*;

/**
 * Class starts the program.
 */
public class StartUI {
	/**
	 * Type of input.
	 */
	private Input input;
	/**
	 * Type of Tracker.
	 */
	private Tracker tracker;
	/**
	 * Default constructor.
	 * @param input Type of input.
	 * @param tracker Type of tracker.
	 */
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	/**
	 * Initiate controllers of the program.
	 * @param menu Type of tracker.
	 */
	public void init(MenuTracker menu) {

		if (!this.isDbEmpty()) {
			this.createNewDb();
		}
		do {
			menu.show();
			int key = Integer.valueOf(input.ask("Выберете действие: ",  menu.getRange()));
			menu.select(key);
		} while (!"y".equals(this.input.ask("Выход? (y/n)")));
	}

	private boolean isDbEmpty() {
	    return true;
    }

    private void createNewDb() {

    }

	/**
	 * Starts the program in operating system.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
//		Tracker tracker = new Tracker();

		Input input = new ValidateInput();
        String url = "jdbc:postgresql://localhost:5432/java_from_a_to_z";
        String username = "postgres";
        String password = "absurdus";
        Connection conn = null;
        try {
			Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Tracker tracker = new Tracker(conn);
		MenuTracker menu = new MenuTracker(input, tracker);
		menu.fillActions();

		new StartUI(input, tracker).init(menu);
	}
}