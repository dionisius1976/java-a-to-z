package ru.dionisius;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by Dionisius on 26.07.2017.
 * Works with specified database.
 * It can add specified Job class collection
 * to specified database.
 * It can also return the last date from all
 * entries at specified database.
 */
public class DbJobManager {

    /**
     * File with specified database properties.
     */
    private final String propertiesFile;

    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(JobsCollector.class);

    /**
     * Properties for specified database connection.
     */
    private final Properties prs = new Properties();

    /**
     * Connection for specified database.
     */
    private java.sql.Connection conn = null;

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
    public DbJobManager(final String propertiesFile) {
        this.propertiesFile = propertiesFile;
        this.connectToDb();
    }

    /**
     * Adds Job class collection to database.
     * @param jobs Job class collection.
     */
    public void add(final Collection<Job> jobs) {
        for(Job job: jobs) {
            try {
                this.st = conn.prepareStatement("INSERT INTO vacancy(title, description, create_date) " +
                        "SELECT ?, ?, ? WHERE NOT EXISTS (SELECT description FROM vacancy WHERE description = ?)");
                st.setString(1, job.getTitle());
                st.setString(2, job.getDescription());
                st.setTimestamp(3, job.getCreate_date());
                st.setString(4, job.getDescription());
                st.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
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
            this.conn = DriverManager.getConnection(prs.getProperty("url"),
                    prs.getProperty("user"), prs.getProperty("password"));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        this.createTable();
    }

    /**
     * Creates the new vacancy table if it doesn't exist.
     */
    private void createTable() {
        try {this.st = this.conn.prepareStatement("CREATE TABLE IF NOT EXISTS vacancy (id serial primary key, " +
                "title text, description text, create_date timestamp);");
            st.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Returns the date of the last vacancy in specified database.
     * @return the date of the last vacancy in specified database.
     */
    public Timestamp getLastVacancyDate() {
        Timestamp lastVacancyDate = null;
        try {
            this.st = this.conn.prepareStatement("SELECT MAX(create_date) FROM vacancy;");
            this.rs = st.executeQuery();
            if (this.rs != null) {
                while (this.rs.next()) {
                    lastVacancyDate = rs.getTimestamp("create_date");
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return lastVacancyDate;
    }

    /**
     * Disconnects from specified database.
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
}
