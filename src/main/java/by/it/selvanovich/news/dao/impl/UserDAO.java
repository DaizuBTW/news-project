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

    private static final String VALUE_PASSWORD = "password";
    private static final String VALUE_ROLE_GUEST = "guest";
    private static final String VALUE_ROLE_TITLE = "title";

    private static final String ERR_MESSAGE_SQL = "sql error";
    private static final String ERR_MESSAGE_CONNECTION_POOL = "error trying to take connection";

    private static final String SQL_AUTHORIZATION_WITH_USERNAME = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_GET_ROLE = "SELECT * FROM users JOIN roles ON users.roles_id = roles.id WHERE users.login = ?";
    private static final String SQL_REGISTRATION = "INSERT INTO users(login, password, roles_id) VALUES (?, ?, 3)";

    @Override
    public boolean authorization(String username, String password) throws DAOException {
        String hashed;
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_AUTHORIZATION_WITH_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                hashed = resultSet.getString(VALUE_PASSWORD);

                return BCrypt.checkpw(password, hashed);
            }
        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
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
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        }
    }

    @Override
    public String getRole(String username) throws DAOException {
        String role = VALUE_ROLE_GUEST;
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ROLE);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                role = rs.getString(VALUE_ROLE_TITLE);
            }
            return role;
        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        }

    }
}
