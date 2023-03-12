package by.it.selvanovich.news.service;

import java.util.List;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.dao.NewsDAOException;

public interface INewsService {

    void update(int id, String title, String brief, String content, String date, String category) throws ServiceException;

    List<News> latestList(int count) throws ServiceException;

    List<News> getList() throws ServiceException;

    News findById(int id) throws ServiceException;

    void addNews(String title, String brief, String content, String date, String category) throws ServiceException;

    void delete(String[] idNewses) throws ServiceException;
}