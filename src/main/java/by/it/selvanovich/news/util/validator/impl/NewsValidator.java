package by.it.selvanovich.news.util.validator.impl;

import by.it.selvanovich.news.bean.News;
import by.it.selvanovich.news.util.validator.INewsValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsValidator implements INewsValidator {

    private static final String REGEX_TITLE = "^[\\w\\W]{1,200}$";
    private static final String REGEX_BRIEF = "^[\\w\\W]{1,400}$";
    private static final String REGEX_CONTENT = "^[\\w\\W]{1,2000}$";
    @Override
    public boolean isTitleValid(String title) {
        if (title != null) {
            Pattern pattern = Pattern.compile(REGEX_TITLE);
            Matcher matcher = pattern.matcher(title);
            return matcher.find();
        } else {
            return false;
        }
    }

    @Override
    public boolean isBriefValid(String brief) {
        if (brief != null) {
            Pattern pattern = Pattern.compile(REGEX_BRIEF);
            Matcher matcher = pattern.matcher(brief);
            return matcher.find();
        } else {
            return false;
        }
    }

    @Override
    public boolean isContentValid(String content) {
        if (content != null) {
            Pattern pattern = Pattern.compile(REGEX_CONTENT);
            Matcher matcher = pattern.matcher(content);
            return matcher.find();
        } else {
            return false;
        }
    }

    @Override
    public boolean isNewsValid(News news) {
        return isTitleValid(news.getTitle()) &&
                isBriefValid(news.getBriefNews()) &&
                isContentValid(news.getContent());
    }
}
