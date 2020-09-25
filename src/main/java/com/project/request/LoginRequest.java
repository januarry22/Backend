package com.project.request;

public class LoginRequest {

	private String userID;
	private String user_passwd;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUser_passwd() {
		return user_passwd;
	}
	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}
	@Override
	public String toString() {
		return "LoginRequest [userID=" + userID + ", user_passwd=" + user_passwd + "]";
	}
	
	
}
