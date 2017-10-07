package ru.dionisius.service;

import ru.dionisius.data.DbManager;
import ru.dionisius.data.IDbManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Dionisius on 03.10.2017.
 */
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        IDbManager dBManager = new DbManager();
        ctx.setAttribute("dBManager", dBManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        IDbManager dBManager = (DbManager) ctx.getAttribute("dBManager");
        dBManager.closeConnection();
    }
}
