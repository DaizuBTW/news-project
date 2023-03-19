package by.it.selvanovich.news.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id = 0;
    private String title = "";
    private String brief = "";
    private String content = "";
    private String date = "";

    private String category = "";

    public News() {
    }

    public News(int id, String title, String brief, String content, String date, String category) {
        super();
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.date = date;
        this.category = category;
    }
    public News(String title, String brief, String content, String date, String category) {
        super();
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.date = date;
        this.category = category;
    }

    public Integer getIdNews() {
        return id;
    }

    public void setIdNews(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefNews() {
        return brief;
    }

    public void setBriefNews(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNewsDate() {
        return date;
    }

    public void setNewsDate(String newsDate) {
        this.date = newsDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        News news = (News) obj;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(brief, news.brief) && Objects.equals(content, news.content) && Objects.equals(date, news.date) && Objects.equals(category, news.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, brief, content, date, category);
    }

}
