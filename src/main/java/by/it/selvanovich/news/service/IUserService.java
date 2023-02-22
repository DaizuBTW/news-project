package by.it.selvanovich.news.service;

import by.it.selvanovich.news.bean.User;

public interface IUserService {
	
	String authorization(String username, String password) throws ServiceException;
	boolean registration(User user) throws ServiceException;

}