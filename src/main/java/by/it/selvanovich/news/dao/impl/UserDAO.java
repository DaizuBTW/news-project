package by.it.selvanovich.news.dao.impl;

import by.it.selvanovich.news.bean.User;
import by.it.selvanovich.news.dao.DAOException;
import by.it.selvanovich.news.dao.IUserDAO;
import by.it.selvanovich.news.dao.connectionPool.ConnectionPool;
import by.it.selvanovich.news.dao.connectionPool.ConnectionPoolException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {
    // TODO закончить внедрение БД

    private static final String SQL_AUTHORIZATION_WITH_USERNAME = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_GET_ROLE = "SELECT * FROM users JOIN roles ON users.roles_id = roles.id WHERE users.login = ?";

    @Override
    public boolean authorization(String username, String password) throws DAOException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {

            PreparedStatement ps = connection.prepareStatement(SQL_AUTHORIZATION_WITH_USERNAME);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            // заглушка до добавления BCrypt
            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    result = true;
                } else {
                    result = false;
                }
            }
            return result;
        } catch (SQLException e) {
            throw new DAOException("sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("error trying to take connection", e);
        }

    }

    @Override
    public boolean registration(String username, String password, String name, String surname, String role) throws DAOException {
        //TODO добавить регистрацию с БД
        try {
            return true;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public String getRole(String username) throws DAOException {
        // TODO доделать негативные исходы
        String role = "guest";
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {

            PreparedStatement ps = connection.prepareStatement(SQL_GET_ROLE);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString("title");
            }
            return role;
        } catch (SQLException e) {
            throw new DAOException("sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("error trying to take connection", e);
        }

    }
}
