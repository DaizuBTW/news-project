package by.it.selvanovich.news.util.validator.impl;

import by.it.selvanovich.news.bean.User;
import by.it.selvanovich.news.util.validator.IUserValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator implements IUserValidator {
    private static String REGEX_NAME = "(^[a-zA-Zа-яА-Я][a-zA-Zа-яА-Я\\S]{0,20}[a-zA-Zа-яА-Я]$)";
    private static String REGEX_USERNAME = "(^[a-zA-Z0-9][a-zA-Z0-9\\S]{0,20}[a-zA-Z0-9]$)";
    private static String REGEX_PASSWORD = "(^[a-zA-Z0-9][a-zA-Z0-9\\S]{6,20}[a-zA-Z0-9]$)";

    @Override
    public boolean isUsernameValid(String username) {
        if (username != null) {
            Pattern pattern = Pattern.compile(REGEX_USERNAME);
            Matcher matcher = pattern.matcher(username);
            return matcher.find();
        } else {
            return false;
        }
    }

    @Override
    public boolean isPasswordValid(String password) {
        if (password != null) {
            Pattern pattern = Pattern.compile(REGEX_PASSWORD);
            Matcher matcher = pattern.matcher(password);
            return matcher.find();
        } else {
            return false;
        }
    }

    @Override
    public boolean isNameValid(String name) {
        if (name != null) {
            Pattern pattern = Pattern.compile(REGEX_NAME);
            Matcher matcher = pattern.matcher(name);
            return matcher.find();
        } else {
            return false;
        }
    }

    @Override
    public boolean isSurnameValid(String surname) {
        if (surname != null) {
            Pattern pattern = Pattern.compile(REGEX_NAME);
            Matcher matcher = pattern.matcher(surname);
            return matcher.find();
        } else {
            return false;
        }
    }

    @Override
    public boolean isUserValid(User user) {
        return isUsernameValid(user.getUsername()) &&
                isPasswordValid(user.getPassword()) &&
                isNameValid(user.getName()) &&
                isSurnameValid(user.getSurname());
    }
}
