package by.it.selvanovich.news.dao.impl;

import by.it.selvanovich.news.bean.User;
import by.it.selvanovich.news.bean.UserList;
import by.it.selvanovich.news.dao.DAOException;
import by.it.selvanovich.news.dao.IUserDAO;

public class UserDAO implements IUserDAO {
    private final UserList userList = new UserList();

    @Override
    public boolean authorization(String username, String password) throws DAOException {
        try {
            boolean result;
            if (userList.containsKey(username)) {
                result = userList.getPassword(username).equals(password);
            } else {
                result = false;
            }
            return result;
        } catch (Exception e) {
            throw new DAOException(e);
        }

    }

    @Override
    public boolean registration(String username, String password, String name, String surname, String role) throws DAOException {
        try {
            userList.put(username, new User(username, password, name, surname, role));
            return true;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public String getRole(String username) throws DAOException {
        try {
            return userList.getRole(username);
        } catch (Exception e) {
            throw new DAOException(e);

        }

    }
}
