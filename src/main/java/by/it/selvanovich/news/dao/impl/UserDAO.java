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
    private static final String SQL_SHOW_USER = "SELECT * FROM users JOIN userdetails u ON users.id = u.users_id WHERE users.login = ?";
    private static final String SQL_SHOW_USER_ROLE = "SELECT * FROM users JOIN roles ON users.roles_id = roles.id WHERE users.login = ?";
    private static final String SQL_ADD_TO_USERS = "INSERT INTO users(login, password, roles_id) VALUES (?, ?, 3)";
    private static final String SQL_ADD_TO_USERDETAILS = "INSERT INTO userdetails(users_id, name, surname) VALUES (?, ?, ?)";
    private static final String SQL_LAST_USER_ID = "SELECT id FROM users ORDER BY id DESC LIMIT 1";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean authorization(String username, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String hashed;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_AUTHORIZATION_WITH_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                hashed = resultSet.getString(VALUE_PASSWORD);

                return BCrypt.checkpw(password, hashed);
            }
        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public boolean registration(User user) throws DAOException {
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int id;
        try {
            connection = connectionPool.takeConnection();
            try {
                preparedStatement = connection.prepareStatement(SQL_ADD_TO_USERS);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException(ERR_MESSAGE_SQL, e);
            }
            try {
                preparedStatement = connection.prepareStatement(SQL_LAST_USER_ID);
                ResultSet set = preparedStatement.executeQuery();
                if (set.next()) {
                    id = set.getInt(1);
                } else {
                    throw new DAOException("no role with such title found in db");
                }
                try {

                    preparedStatement = connection.prepareStatement(SQL_ADD_TO_USERDETAILS);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getSurname());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new DAOException(ERR_MESSAGE_SQL, e);
                }
            } catch (SQLException e) {
                throw new DAOException(ERR_MESSAGE_SQL, e);
            }
            System.out.println("nice");
            return true;
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public String getRole(String username) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String role = VALUE_ROLE_GUEST;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_SHOW_USER_ROLE);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString(VALUE_ROLE_TITLE);
            }
            return role;
        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }

    }

    @Override
    public User getUserDetails(String username) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String name = null;
        String surname = null;
        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(SQL_SHOW_USER);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
            }
            return new User(null, null, name, surname, null);
        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
