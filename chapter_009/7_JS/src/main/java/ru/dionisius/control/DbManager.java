package ru.dionisius.control;

import ru.dionisius.model.User;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dionisius on 26.07.2017.
 * Works with specified database.
 * It can add new user, edit user's personal ru.dionisius.data,
 * delete specified user, return list of all users
 * and find specified user.
 */
public class DbManager {

    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbManager.class);

    /**
     * Properties for specified database connection.
     */
    private final Properties prs = new Properties();

    /**
     * Properties for specified database connection.
     */
    private final String propertiesFile = "config.properties";

    /**
     * A fully qualified Java class name of a Driver implementation.
     */
    private static final String SQL_DRIVER = "org.postgresql.Driver";

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
     * Database manager instance reference.
     */
    private static final DbManager INSTANCE = new DbManager();

    /**
     * Constructor.
     */
    private DbManager() {
        this.loadProperties();
        this.connectToDb();
        this.createTable();
    }

    /**
     * Returns database manager instance.
     * @return database manager instance.
     */
    public static DbManager getInstance() {
        return INSTANCE;
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
     * Creates the new vacancy table if it doesn't exist.
     */
    private void createTable() {
        try {
            this.st = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS users2 (id serial primary key, "
                + "name VARCHAR(100), login VARCHAR(100), email VARCHAR(100), country VARCHAR(100), " +
                    "city VARCHAR(100), create_date timestamp);");
            st.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Creates new user.
     * @param userName user name.
     * @param userLogin user login.
     * @param userEmail user email.
     * @param userCountry user country.
     * @param userCity user city.
     */
    public void createUser(final String userName, final String userLogin, final String userEmail,
                            final String userCountry, final String userCity) {
        if (!userLogin.equals("") && !userName.equals("")) {
            try {
                this.st = conn.prepareStatement("INSERT INTO users2 (name, login, email, country, city, create_date) "
                        + "SELECT ?, ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT name, login FROM users2 "
                        + "WHERE name = ? AND login = ?);");
                st.setString(1, userName);
                st.setString(2, userLogin);
                st.setString(3, userEmail);
                st.setString(4, userCountry);
                st.setString(5, userCity);
                st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                st.setString(7, userName);
                st.setString(8, userLogin);
                st.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    public void editUser(final String userName,
                         final String userLogin,
                         final String newUserLogin,
                         final String newUserEmail,
                         final String newUserCountry,
                         final String newUserCity) {
        try {
            this.st = conn.prepareStatement("UPDATE users2 SET login = ?, email = ?, country = ?," +
                                                "city = ? WHERE name = ? AND login = ?;");
            st.setString(1, newUserLogin);
            st.setString(2, newUserEmail);
            st.setString(3, newUserCountry);
            st.setString(4, newUserCity);
            st.setString(5, userName);
            st.setString(6, userLogin);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Deletes specified user.
     * @param userName user's name.
     * @param userLogin user's login.
     */
    public void deleteUser(final String userName, final String userLogin) {
        try {
            this.st = conn.prepareStatement("DELETE FROM users2 WHERE name = ? AND login = ?;");
            st.setString(1, userName);
            st.setString(2, userLogin);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public User getUser(final String userName, final String userLogin) {
        User user = null;
        try {
            this.st = conn.prepareStatement("SELECT * FROM users2 WHERE name = ? AND login = ?;");
            st.setString(1, userName);
            st.setString(2, userLogin);
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                user = new User(this.rs.getString("name"),
                        this.rs.getString("login"),
                        this.rs.getString("email"),
                        this.rs.getString("country"),
                        this.rs.getString("city"),
                        this.rs.getTimestamp("create_date"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    /**
     * Returns all users from database.
     * @return all users from database.
     */
    public List<User> getAllUsers() {
        List<User> allUsers = new LinkedList<>();
        try {
            this.st = conn.prepareStatement("SELECT * FROM users2;");
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                allUsers.add(new User(this.rs.getString("name"),
                        this.rs.getString("login"),
                        this.rs.getString("email"),
                        this.rs.getString("country"),
                        this.rs.getString("city"),
                        this.rs.getTimestamp("create_date")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return allUsers;
    }

    /**
     * Connects to specified database.
     */
    private void connectToDb() {
        PoolProperties p = new PoolProperties();
        p.setUrl(this.prs.getProperty("url"));
        p.setDriverClassName(SQL_DRIVER);
        p.setUsername(this.prs.getProperty("user"));
        p.setPassword(this.prs.getProperty("password"));
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        try {
            this.conn = datasource.getConnection();
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
    protected void finalize() throws Throwable {
        this.disconnectDb();
        super.finalize();
    }
}
