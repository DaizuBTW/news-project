package by.it.selvanovich.news.service;

import java.util.List;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.dao.NewsDAOException;

public interface INewsService {

    boolean update(int id, String title, String brief, String content, String date, String category) throws ServiceException;

    List<News> latestList(int count) throws ServiceException;

    List<News> getList(String category) throws ServiceException;

    News findById(int id) throws ServiceException;

    boolean addNews(String title, String brief, String content, String date, String category) throws ServiceException;

    boolean delete(String[] idNewses) throws ServiceException;
}