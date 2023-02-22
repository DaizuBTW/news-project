package by.it.selvanovich.news.dao.impl;

import by.it.selvanovich.news.bean.User;
import by.it.selvanovich.news.dao.DAOException;
import by.it.selvanovich.news.dao.IUserDAO;
import by.it.selvanovich.news.dao.connectionPool.ConnectionPool;
import by.it.selvanovich.news.dao.connectionPool.ConnectionPoolException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

    private static final String SQL_AUTHORIZATION_WITH_USERNAME = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_GET_ROLE = "SELECT * FROM users JOIN roles ON users.roles_id = roles.id WHERE users.login = ?";
    private static final String SQL_REGISTRATION = "INSERT INTO users(login, password, roles_id) VALUES (?, ?, 3)";

    @Override
    public boolean authorization(String username, String password) throws DAOException {
        String hashed = null;

        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_AUTHORIZATION_WITH_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                hashed = resultSet.getString("password");

                return BCrypt.checkpw(password, hashed);
            }
        } catch (SQLException e) {
            throw new DAOException("sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("error trying to take connection", e);
        }
        return false;
    }

    @Override
    public boolean registration(User user) throws DAOException {
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_REGISTRATION);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("sql error", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("error trying to take connection", e);
        }
    }

    @Override
    public String getRole(String username) throws DAOException {
        // TODO доделать негативные исходы
        String role = "guest";
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ROLE);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
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
