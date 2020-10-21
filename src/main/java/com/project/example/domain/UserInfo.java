package com.project.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserInfo {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String user_name;
	
	private Collection<? extends GrantedAuthority> authorities;
	private String auth;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
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
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auth);
	}
	
	
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + ", user_name=" + user_name
				+ ", authorities=" + authorities + ", auth=" + auth + "]";
	}
	
	
	
	
}
