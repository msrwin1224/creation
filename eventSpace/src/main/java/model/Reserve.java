package model;

import java.io.Serializable;

public class Reserve implements Serializable {
	private int userId;
	private int id;
	private String userName;
	private String date;
	private String timeStart;
	private String timeEnd;
	private String purpose;

	public Reserve() {
	}

	public Reserve(int userId, int id, String userName, String date, String timeStart, String timeEnd, String purpose) {
		this.userId = userId;
		this.id = id;
		this.userName = userName;
		this.date = date;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.purpose = purpose;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getDate() {
		return date;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public String getPurpose() {
		return purpose;
	}

	public int getId() {
		return id;
	}

}