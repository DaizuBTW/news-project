package by.it.selvanovich.news.controller;

import by.it.selvanovich.news.dao.ConnectionPoolException;
import by.it.selvanovich.news.dao.impl.ConnectionPool;
import jakarta.servlet.ServletContextEvent;

public class ServletContextListener implements jakarta.servlet.ServletContextListener {
    // TODO прописать дестрой
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().initPoolData();
            System.out.println("Connection pool init");
        } catch (ConnectionPoolException e) {
            System.out.println("error");
            throw new RuntimeException("error during init of connection pool", e);
        }


        jakarta.servlet.ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        jakarta.servlet.ServletContextListener.super.contextDestroyed(sce);
    }
}
