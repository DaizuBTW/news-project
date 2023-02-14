package by.it.selvanovich.news.dao.impl;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.bean.NewsList;
import by.it.selvanovich.news.dao.INewsDAO;
import by.it.selvanovich.news.dao.NewsDAOException;

import java.sql.*;
import java.util.*;

public class NewsDAO implements INewsDAO {
    // TODO дописать оставшиеся методы

    private static final String SQL_SHOW_LIST = "SELECT * FROM news";
    private static final String SQL_SHOW_BY_ID = "SELECT * FROM news WHERE id = ?";
    private static final String SQL_ADD_NEWS = "INSERT INTO news(content,title,brief,users_id) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE_NEWS = "UPDATE news SET content=?,title=?,brief=?,users_id=? WHERE id = ?";
    private static final String SQL_DELETE_NEWS = "DELETE FROM news WHERE id = ?";


    private final NewsList newsList = new NewsList();

    @Override
    public List<News> getList() throws NewsDAOException {
        List<News> listResult = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_SHOW_LIST);
            while (rs.next()) {
                listResult.add(new News(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(2), rs.getString(5)));
            }
            return listResult;
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public List<News> getLatestList(int count) throws NewsDAOException {
        List<News> result = new ArrayList<>();

        try {
            if (newsList.size() < count) {
                result = newsList.getList();
            } else {
                for (int i = 0; i < count; i++) {
                    result.add(newsList.get(newsList.size() - count + i));
                }
            }
            return result;
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }

    }

    @Override
    public News fetchById(int id) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_SHOW_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new News(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(2), rs.getString(5));
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public int addNews(News news) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_ADD_NEWS);
            ps.setString(1, news.getContent());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getBriefNews());
            //ps.setDate(4, Date.valueOf(news.getNewsDate()));
            ps.setInt(4, 1);

            ps.executeUpdate();
            newsList.add(news);
            return news.getIdNews();
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public void updateNews(int id, News news) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_NEWS);
            ps.setString(1, news.getContent());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getBriefNews());
            ps.setInt(4, 1);
            ps.setInt(5, id);

            ps.executeUpdate();
            newsList.updateNews(id, news);
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override

    public void deleteNewses(String[] idNewses) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE_NEWS);
            if (idNewses != null) {
                for (String id : idNewses) {
                    ps.setInt(1, Integer.parseInt(id));
                    ps.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public int getListSize() throws NewsDAOException {
        try {
            return newsList.size();
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }
}
