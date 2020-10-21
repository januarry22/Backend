package com.project.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.project.example.domain.User;
import com.project.example.domain.UserInfo;

@Mapper
public interface UserMapper {
	//유저읽기
	public User readUser(String username);
	
	// 유저 생성
	public void createUser(User user);
	
	// 권한 읽기
	public List<GrantedAuthority> readAuthorities(String username);

	// 권한 생성
	public void createAuthority(User user);
	
	
	// 유저 새로고침
	public UserInfo readUser_refresh(String username);
	
	// 유저 권한 읽기 새로고침
	public List<GrantedAuthority> readAuthorities_refresh(String username);
	
	
}
