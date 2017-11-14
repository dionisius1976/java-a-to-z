package ru.dionisius.service.listeners;

import ru.dionisius.data.dbtools.DbManager;
import ru.dionisius.data.dbtools.IDbManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Dionisius on 03.10.2017.
 * Writes DbManager instance in servletContext when servlet is created.
 * Closes hibernate session factory that uses DbManager when application is closing.
 */
public class AppContextListener implements ServletContextListener {
    /**
     * Writes DbManager instance in servletContext when servlet is created.
     * @param servletContextEvent servletContext instance of this application.
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        IDbManager dBManager = new DbManager();
        ctx.setAttribute("dBManager", dBManager);
    }

    /**
     * Closes hibernate session factory that uses DbManager when application is closing.
     * @param servletContextEvent servletContext instance of this application.
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        IDbManager dBManager = (DbManager) ctx.getAttribute("dBManager");
        dBManager.closeSessionFactory();
    }
}
