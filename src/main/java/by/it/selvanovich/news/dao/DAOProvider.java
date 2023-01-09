package by.it.selvanovich.news.dao;

import by.it.selvanovich.news.dao.impl.NewsDAO;
import by.it.selvanovich.news.dao.impl.UserDAO;

public final class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final IUserDAO userDao = new UserDAO();
    private final INewsDAO newsDAO = new NewsDAO();


    private DAOProvider() {
    }


    public IUserDAO getUserDao() {
        return userDao;
    }

    public INewsDAO getNewsDAO() {
        return newsDAO;
    }

    public static DAOProvider getInstance() {
        return instance;
    }
}