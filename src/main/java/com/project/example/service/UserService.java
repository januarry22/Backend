package com.project.example.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.example.domain.User;

public interface UserService extends UserDetailsService{

	// 유저 읽기
	public User readUser(String username);
	
	// 유저 생성
	public void createUser(User user);
		
	// 시큐리티 권한 얻기
	Collection<GrantedAuthority> getAuthorities(String username);

	// 권한 생성
	public void createAuthority(User user);
}
