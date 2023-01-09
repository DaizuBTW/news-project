package by.it.selvanovich.news.dao.impl;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.bean.NewsList;
import by.it.selvanovich.news.dao.INewsDAO;
import by.it.selvanovich.news.dao.NewsDAOException;

import java.util.*;

public class NewsDAO implements INewsDAO {

    private final NewsList newsList = new NewsList();

    @Override
    public List<News> getList() throws NewsDAOException {
        try {
            return newsList.getList();
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
        try {
            News news = null;
            for (News obj : newsList.getList()) {
                if (obj.getIdNews().equals(id)) {
                    news = obj;
                }
            }
            return news;
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public int addNews(News news) throws NewsDAOException {
        try {
            newsList.add(news);
            return news.getIdNews();
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public void updateNews(int id, News news) throws NewsDAOException {
        try {
            newsList.updateNews(id, news);
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public void deleteNewses(String[] idNewses) throws NewsDAOException {
        try {
            if (idNewses != null) {
                for (String id : idNewses) {
                    for (News obj : newsList.getList()) {
                        if (obj.getIdNews().equals(Integer.parseInt(id))) {
                            newsList.deleteNews(obj);
                            break;
                        }
                    }
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

    @Override
    public News getNews(int id) throws NewsDAOException {
        try {
            return newsList.get(id);
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }
}
