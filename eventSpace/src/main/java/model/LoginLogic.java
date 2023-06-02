package model;

import dao.UserDAO;

public class LoginLogic {
	public boolean login(String name, String pass) {

		UserDAO dao = new UserDAO();
		boolean bool = dao.login(name, pass);

		if (bool == true) {
			return true;
		}
		return false;
	}

	public boolean loginAdmin(String name, String pass, boolean admin) {

		UserDAO dao = new UserDAO();
		boolean bool = dao.loginAdmin(name, pass, admin);

		if (bool == true) {
			return true;
		}
		return false;
	}

	public int userIdGet(String name, String pass) {

		UserDAO dao = new UserDAO();
		int uId = dao.userIdGet(name, pass);

		return uId;
	}
}