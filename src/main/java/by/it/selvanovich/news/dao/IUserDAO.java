package by.it.selvanovich.news.dao;

import by.it.selvanovich.news.bean.User;

public interface IUserDAO {

    boolean authorization(String username, String password) throws DAOException;

    boolean registration(String username, String password, String name, String surname, String role) throws DAOException;

    String getRole(String username) throws DAOException;
}
