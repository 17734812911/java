package com.xtw.entity;

public class User {
	
	private int userId;
	private String userName;
	private String passWord;
	private String phone;
	private int status;
	public User() {
		super();
	}
	public User(int userId, String userName, String passWord, String phone, int status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.phone = phone;
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", phone=" + phone
				+ ", status=" + status + "]";
	}
	
}
