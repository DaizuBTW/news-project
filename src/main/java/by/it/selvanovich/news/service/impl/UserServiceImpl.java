package by.it.selvanovich.news.service.impl;

import by.it.selvanovich.news.bean.User;
import by.it.selvanovich.news.dao.DAOException;
import by.it.selvanovich.news.dao.DAOProvider;
import by.it.selvanovich.news.dao.IUserDAO;
import by.it.selvanovich.news.service.IUserService;
import by.it.selvanovich.news.service.ServiceException;

public class UserServiceImpl implements IUserService {
    // TODO переработать сервисы для работы с БД

    private final IUserDAO userDAO = DAOProvider.getInstance().getUserDao();

    @Override
    public String authorization(String username, String password) throws ServiceException {
        try {
            if(userDAO.authorization(username, password)) {
                return userDAO.getRole(username);
            }else {
                return "guest";
            }

        }catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(String username, String password, String name, String surname) throws ServiceException {
        try {
            userDAO.registration(username, password, name, surname, "user");
            return true;
        }catch(DAOException e) {
            throw new ServiceException(e);
        }

    }
}
