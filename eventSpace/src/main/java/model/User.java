package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String name;
	private String pass;
	private boolean login;
	private boolean admin = false;

	public User() {
	}

	public User(String name, String pass, boolean login, boolean admin) {
		this.name = name;
		this.pass = pass;
		this.login = login;
		this.admin = admin;
	}

	public User(int id, String name, String pass, boolean login, boolean admin) {
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.login = login;
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public boolean login() {
		return login;

	}

	public boolean admin() {
		return admin;
	}

	public int getId() {
		return id;
	}
}