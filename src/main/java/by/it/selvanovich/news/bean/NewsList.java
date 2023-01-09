package by.it.selvanovich.news.bean;

import java.util.ArrayList;
import java.util.List;

public class NewsList {
    private final List<News> newsList;

    public NewsList() {
        this.newsList = new ArrayList<>();
    }

    public void add(News news) {
        this.newsList.add(news);
    }

    public News get(int id) {
        return newsList.get(id - 1);
    }

    public List<News> getList() {
        return this.newsList;
    }

    public int size() {
        return this.newsList.size();
    }

    public void updateNews(int id, News news) {
        for (News obj : newsList) {
            if (obj.getIdNews().equals(id)) {
                newsList.set(newsList.indexOf(obj), news);
            }
        }
    }
    public void deleteNews(News news) {
        newsList.remove(news);
    }
}
