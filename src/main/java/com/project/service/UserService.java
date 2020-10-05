package com.project.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.domain.User;

public interface UserService extends UserDetailsService{

	//유저읽기
	public User readUser(String userID);
		
	// 시큐리티 권한 얻기
	Collection<GrantedAuthority> getAuthorities(String userID);

	// 권한 생성
	public void createAuthority(User user);
}
