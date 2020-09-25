package com.project.response;

import java.util.List;

public class JwtResponse {

	private String token;
	private String userID;;
	private String user_name;
	private List<String> roles;
	private String type ="Bearer";
	
	
	
	public JwtResponse(String jwt, String u_userID, String u_name, List<String> u_roles) {
		this.token = jwt;
		this.userID = u_userID;
		this.user_name = u_name;
		this.roles = u_roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", userID=" + userID + ", user_name=" + user_name + ", roles=" + roles
				+ "]";
	}

	
	
	
	
}
