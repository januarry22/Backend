package com.project.example.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.project.example.domain.User;
import com.project.example.domain.UserInfo;

public interface UserService extends UserDetailsService{

	// 유저 읽기
	public User readUser(String username);
	
	// 유저 생성
	public void createUser(User user);
		
	// 시큐리티 권한 얻기
	Collection<GrantedAuthority> getAuthorities(String username);

	// 권한 생성
	public void createAuthority(User user);
	
	
	// 유저 새로고침
	public UserInfo readUser_refresh(String username);
	
	// 유저 권한 읽기 새로고침
	//public List<GrantedAuthority> readAuthorities_refresh(String username);
	
}
