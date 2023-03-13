package by.it.selvanovich.news.dao;

import by.it.selvanovich.news.bean.News;

import java.util.List;

public interface INewsDAO {
    List<News> getList() throws NewsDAOException;
    List<News> getListByFilter(int category) throws NewsDAOException;

    List<News> getLatestList(int count) throws NewsDAOException;

    News fetchById(int id) throws NewsDAOException;

    int addNews(News news) throws NewsDAOException;

    void updateNews(int id, News news) throws NewsDAOException;

    void deleteNewses(String[] idNewses) throws NewsDAOException;

}
