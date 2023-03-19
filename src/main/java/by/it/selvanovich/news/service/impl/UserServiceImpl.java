package by.it.selvanovich.news.service.impl;

import by.it.selvanovich.news.bean.User;
import by.it.selvanovich.news.dao.DAOException;
import by.it.selvanovich.news.dao.DAOProvider;
import by.it.selvanovich.news.dao.IUserDAO;
import by.it.selvanovich.news.service.IUserService;
import by.it.selvanovich.news.service.ServiceException;
import by.it.selvanovich.news.util.validator.IUserValidator;
import by.it.selvanovich.news.util.validator.ValidatorProvider;

import java.util.concurrent.locks.ReentrantLock;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DAOProvider.getInstance().getUserDao();
    private final IUserValidator userValidator = ValidatorProvider.getInstance().getUserValidator();
    private final static ReentrantLock locker = new ReentrantLock();

    @Override
    public String authorization(String username, String password) throws ServiceException {
        try {
            if (userValidator.isUsernameValid(username) &&
                    userValidator.isPasswordValid(password) &&
                    userDAO.authorization(username, password)) {
                return userDAO.getRole(username);
            } else {
                return "guest";
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(User user) throws ServiceException {
        locker.lock();
        try {
            if (userValidator.isUserValid(user)) {
                userDAO.registration(user);
                return true;
            } else {
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            locker.unlock();
        }
    }

    @Override
    public User getUserDetails(String username) throws ServiceException {
        try {
            return userDAO.getUserDetails(username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
