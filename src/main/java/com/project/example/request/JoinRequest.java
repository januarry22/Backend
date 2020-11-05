package com.project.example.request;

public class JoinRequest {

	private String username;
	private String password;
	private String user_name;

	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Override
	public String toString() {
		return "JoinRequest [username=" + username + ", password=" + password + ", user_name=" + user_name + "]";
	}
	
	
}
