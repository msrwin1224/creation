package model;

import dao.UserDAO;

public class SignUpLogic {

	public void signUp(String name, String pass, String mail, int admin) {

		UserDAO userDao = new UserDAO();
		userDao.signUp(name, pass, mail, admin);
	}
}
