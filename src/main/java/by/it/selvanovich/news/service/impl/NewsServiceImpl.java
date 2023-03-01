package by.it.selvanovich.news.service.impl;

import java.util.List;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.dao.DAOProvider;
import by.it.selvanovich.news.dao.INewsDAO;
import by.it.selvanovich.news.dao.NewsDAOException;
import by.it.selvanovich.news.service.INewsService;
import by.it.selvanovich.news.service.ServiceException;

public class NewsServiceImpl implements INewsService {

    private final INewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();

    @Override
    public void update(int id, String title, String brief, String content, String date) throws ServiceException {
        try {
            newsDAO.updateNews(id, new News(id, title, brief, content, date));
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> latestList(int count) throws ServiceException {

        try {
            return newsDAO.getLatestList(5);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getList() throws ServiceException {
        try {
            return newsDAO.getList();
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public News findById(int id) throws ServiceException {
        try {
            return newsDAO.fetchById(id);
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addNews(String title, String brief, String content, String date) throws ServiceException {
        try {
            newsDAO.addNews(new News(1, title, brief, content, date));
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(String[] idNewses) throws ServiceException {
        try {
            newsDAO.deleteNewses(idNewses);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}