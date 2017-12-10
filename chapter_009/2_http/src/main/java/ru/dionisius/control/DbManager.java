package ru.dionisius.control;

import ru.dionisius.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Dionisius on 26.07.2017.
 * Works with specified database.
 * It can add new user, edit user's personal ru.dionisius.ru.dionisius.data,
 * delete specified user and find specified user.
 */
public class DbManager implements IDbManager {

    /**
     * File with specified database properties.
     */
    private final String propertiesFile;

    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbManager.class);

    /**
     * Properties for specified database connection.
     */
    private final Properties prs = new Properties();

    /**
     * Connection for specified database.
     */
    private Connection conn = null;

    /**
     * Statement for specified connection.
     */
    private PreparedStatement st = null;
    /**
     * ResultSet instance of specified query.
     */
    private ResultSet rs = null;

    /**
     * Constructor.
     * @param propertiesFile file with database properties.
     */
    public DbManager(final String propertiesFile) {
        this.propertiesFile = propertiesFile;
        this.connectToDb();
    }

    /**
     * Loads properties.
     */
    private void loadProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            this.prs.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Connects to specified database.
     */
    private void connectToDb() {
        this.loadProperties();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.conn = DriverManager.getConnection(this.prs.getProperty("url"),
                    this.prs.getProperty("user"), this.prs.getProperty("password"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        this.createTable();
    }

    /**
     * Creates the new vacancy table if it doesn't exist.
     */
    private void createTable() {
        try {
            this.st = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS users (id serial primary key, "
                + "name VARCHAR(100), login VARCHAR(100), email VARCHAR(100), create_date timestamp);");
            st.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Disconnects from working database.
     */
    public void disconnectDb() {
        try {
            this.conn.close();
            this.st.close();
            this.rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (this.conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (this.st != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (this.rs != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    public void createUser(final String userName, final String userLogin, final String userEmail) {
        try {
            this.st = conn.prepareStatement("INSERT INTO users (name, login, email, create_date) "
                   + "SELECT ?, ?, ?, ? WHERE NOT EXISTS (SELECT name, login FROM users "
                   + "WHERE name = ? AND login = ?);");
            st.setString(1, userName);
            st.setString(2, userLogin);
            st.setString(3, userEmail);
            st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            st.setString(5, userName);
            st.setString(6, userLogin);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void editUser(final String userName,
                         final String userLogin,
                         final String newUserLogin,
                         final String newUserEmail) {
        try {
            this.st = conn.prepareStatement("UPDATE users SET login = ?, email = ? "
                    + "WHERE name = ? AND login = ?;");
            st.setString(1, newUserLogin);
            st.setString(2, newUserEmail);
            st.setString(3, userName);
            st.setString(4, userLogin);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(final String userName, final String userLogin) {
        try {
            this.st = conn.prepareStatement("DELETE FROM users WHERE name = ? AND login = ?;");
            st.setString(1, userName);
            st.setString(2, userLogin);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public User getUser(final String userName, final String userLogin) {
        User user = null;
        try {
            this.st = conn.prepareStatement("SELECT * FROM users WHERE name = ? AND login = ?;");
            st.setString(1, userName);
            st.setString(2, userLogin);
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                user = new User(this.rs.getString("name"),
                        this.rs.getString("login"),
                        this.rs.getString("email"),
                        this.rs.getTimestamp("create_date"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }
}
