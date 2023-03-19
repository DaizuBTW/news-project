package by.it.selvanovich.news.service.impl;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.dao.DAOProvider;
import by.it.selvanovich.news.dao.INewsDAO;
import by.it.selvanovich.news.dao.NewsDAOException;
import by.it.selvanovich.news.service.INewsService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.util.validator.INewsValidator;
import by.it.selvanovich.news.util.validator.ValidatorProvider;

public class NewsServiceImpl implements INewsService {

    private final INewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();
    private final INewsValidator newsValidator = ValidatorProvider.getInstance().getNewsValidator();
    private final static ReentrantLock locker = new ReentrantLock();

    @Override
    public boolean update(int id, String title, String brief, String content, String date, String category) throws ServiceException {
        locker.lock();
        try {
            News news = new News(id, title, brief, content, date, category);
            if (newsValidator.isNewsValid(news)) {
                newsDAO.updateNews(id, news);
                return true;
            } else {
                return false;
            }
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        } finally {
            locker.unlock();
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
    public List<News> getList(String category) throws ServiceException {
        try {
            if (category == null || category.equals("0")) {
                return newsDAO.getList();
            } else {
                return newsDAO.getListByFilter(Integer.parseInt(category));
            }
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
    public boolean addNews(String title, String brief, String content, String date, String category) throws ServiceException {
        locker.lock();
        try {
            News news = new News(title, brief, content, date, category);
            if (newsValidator.isNewsValid(news)) {
                newsDAO.addNews(new News(title, brief, content, date, category));
                return true;
            } else {
                return false;
            }
        } catch (NewsDAOException e) {
            throw new ServiceException(e);
        } finally {
            locker.unlock();
        }
    }

    @Override
    public boolean delete(String[] idNewses) throws ServiceException {
        locker.lock();
        try {
            newsDAO.deleteNewses(idNewses);
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        } finally {
            locker.unlock();
        }
    }
}