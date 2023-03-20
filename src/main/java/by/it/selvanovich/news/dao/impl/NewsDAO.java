package by.it.selvanovich.news.dao.impl;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.dao.INewsDAO;
import by.it.selvanovich.news.dao.NewsDAOException;
import by.it.selvanovich.news.dao.connectionPool.ConnectionPool;
import by.it.selvanovich.news.dao.connectionPool.ConnectionPoolException;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class NewsDAO implements INewsDAO {

    private static final String ERR_MESSAGE_SQL = "sql error";
    private static final String ERR_MESSAGE_CONNECTION_POOL = "error trying to take connection";

    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

    private static final String SQL_SHOW_LIST = "SELECT id, title, brief, content, date, category " +
            "FROM news JOIN newscategory n on news.category_id = n.news_category_id ORDER BY date DESC";
    private static final String SQL_SHOW_LIST_BY_FILTER = "SELECT id, title, brief, content, date, category " +
            "FROM news JOIN newscategory n on news.category_id = n.news_category_id WHERE category_id = ? ORDER BY date DESC";
    private static final String SQL_SHOW_LAST_NEWS_LIST = "SELECT id, title, brief, content, date, category " +
            "FROM news JOIN newscategory n ON category_id = n.news_category_id ORDER BY date DESC LIMIT ?";
    private static final String SQL_SHOW_BY_ID = "SELECT id, title, brief, content, date, category " +
            "FROM news JOIN newscategory n on news.category_id = n.news_category_id WHERE id = ?";
    private static final String SQL_ADD_NEWS = "INSERT INTO news(content,title,brief,date,category_id,users_id) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_NEWS = "UPDATE news SET content=?,title=?,brief=?,date=?,category_id=?,users_id=? WHERE id = ?";
    private static final String SQL_DELETE_NEWS = "DELETE FROM news WHERE id = ?";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<News> getList() throws NewsDAOException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        List<News> listResult = new ArrayList<>();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            con = connectionPool.takeConnection();
            st = con.createStatement();
            rs = st.executeQuery(SQL_SHOW_LIST);

            while (rs.next()) {
                String date = formatter.format(rs.getTimestamp(5));
                listResult.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), date, rs.getString(6)));
            }

            return listResult;

        } catch (SQLException e) {
            throw new NewsDAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, st, rs);
        }
    }

    @Override
    public List<News> getListByFilter(int category) throws NewsDAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<News> listResult = new ArrayList<>();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SHOW_LIST_BY_FILTER);
            ps.setInt(1, category);
            rs = ps.executeQuery();

            while (rs.next()) {
                String date = formatter.format(rs.getTimestamp(5));
                listResult.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), date, rs.getString(6)));
            }

            return listResult;

        } catch (SQLException e) {
            throw new NewsDAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }

    @Override
    public List<News> getLatestList(int count) throws NewsDAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<News> listResult = new ArrayList<>();

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SHOW_LAST_NEWS_LIST);
            ps.setInt(1, count);
            rs = ps.executeQuery();

            while (rs.next()) {
                String date = formatter.format(rs.getTimestamp(5));
                listResult.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), date, rs.getString(6)));
            }

            return listResult;

        } catch (SQLException e) {
            throw new NewsDAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }

    @Override
    public News fetchById(int id) throws NewsDAOException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SQL_SHOW_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            String date = formatter.format(rs.getTimestamp(5));

            return new News(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), date, rs.getString(6));

        } catch (SQLException e) {
            throw new NewsDAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }

    @Override
    public int addNews(News news) throws NewsDAOException {

        Timestamp timestamp = Timestamp.valueOf(news.getNewsDate().replace("T", " ") + ":00");
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_ADD_NEWS);
            ps.setString(1, news.getContent());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getBriefNews());
            ps.setTimestamp(4, timestamp);
            ps.setInt(5, Integer.parseInt(news.getCategory()));
            ps.setInt(6, 1);
            ps.executeUpdate();

            con.commit();
            return news.getIdNews();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new NewsDAOException(ERR_MESSAGE_SQL, e);
            }
            throw new NewsDAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void updateNews(int id, News news) throws NewsDAOException {

        Timestamp timestamp = Timestamp.valueOf(news.getNewsDate().replace("T", " ") + ":00");
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_UPDATE_NEWS);
            ps.setString(1, news.getContent());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getBriefNews());
            ps.setTimestamp(4, timestamp);
            ps.setInt(5, Integer.parseInt(news.getCategory()));
            ps.setInt(6, 1);
            ps.setInt(7, id);
            ps.executeUpdate();

            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new NewsDAOException(ERR_MESSAGE_SQL, e);
            }
            throw new NewsDAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }

    @Override
    public void deleteNewses(String[] idNewses) throws NewsDAOException {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_DELETE_NEWS);

            if (idNewses != null) {
                for (String id : idNewses) {
                    ps.setInt(1, Integer.parseInt(id));
                    ps.executeUpdate();
                }
            }

            con.commit();

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new NewsDAOException(ERR_MESSAGE_SQL, e);
            }
            throw new NewsDAOException(ERR_MESSAGE_SQL, e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(ERR_MESSAGE_CONNECTION_POOL, e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
