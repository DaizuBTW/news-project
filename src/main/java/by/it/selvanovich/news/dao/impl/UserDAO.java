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
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String hashed;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_AUTHORIZATION_WITH_USERNAME);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                hashed = rs.getString(VALUE_PASSWORD);

                return BCrypt.checkpw(password, hashed);
            }
        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return false;
    }

    @Override
    public boolean registration(User user) throws DAOException {

        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        Connection con = null;
        PreparedStatement ps = null;
        int id;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            try {
                ps = con.prepareStatement(SQL_ADD_TO_USERS);
                ps.setString(1, user.getUsername());
                ps.setString(2, password);
                ps.executeUpdate();

            } catch (SQLException e) {
                throw new DAOException(ERR_MESSAGE_SQL, e);
            }
            try {
                ps = con.prepareStatement(SQL_LAST_USER_ID);
                ResultSet set = ps.executeQuery();

                if (set.next()) {
                    id = set.getInt(1);
                } else {
                    throw new DAOException(ERR_MESSAGE_SQL);
                }

                try {
                    ps = con.prepareStatement(SQL_ADD_TO_USERDETAILS);
                    ps.setInt(1, id);
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getSurname());
                    ps.executeUpdate();
                } catch (SQLException e) {
                    throw new DAOException(ERR_MESSAGE_SQL, e);
                }

            } catch (SQLException e) {
                throw new DAOException(ERR_MESSAGE_SQL, e);
            }

            con.commit();
            return true;

        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DAOException(ERR_MESSAGE_SQL, e);
            }
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public String getRole(String username) throws DAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SHOW_USER_ROLE);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(VALUE_ROLE_TITLE);
            } else {
                return VALUE_ROLE_GUEST;
            }

        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }

    }

    @Override
    public User getUserDetails(String username) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = null;
        String surname = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SHOW_USER);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString("name");
                surname = rs.getString("surname");
            }

            return new User(name, surname);
        } catch (SQLException e) {
            throw new DAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }
}
