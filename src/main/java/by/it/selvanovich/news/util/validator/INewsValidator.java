package by.it.selvanovich.news.util.validator;

import by.it.selvanovich.news.bean.News;

public interface INewsValidator {
    boolean isTitleValid(String title);
    boolean isBriefValid(String brief);
    boolean isContentValid(String content);
    boolean isNewsValid(News news);
}
