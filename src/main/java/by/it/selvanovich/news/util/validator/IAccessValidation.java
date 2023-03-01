package by.it.selvanovich.news.util.validator;

import jakarta.servlet.http.HttpSession;

public interface IAccessValidation {
	
	boolean haveSession(HttpSession session);
	boolean haveAdminPermissions(HttpSession session);
	boolean haveAuthorizedUser(HttpSession session);

}