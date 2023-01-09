package by.it.selvanovich.news.service;

import by.it.selvanovich.news.bean.User;

public interface IUserService {
	
	String authorization(String username, String password) throws ServiceException;
	boolean registration(String username, String password, String name, String surname) throws ServiceException;

}