package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.project.domain.User;

@Mapper
public interface UserMapper {
	//유저읽기
	public User readUser(String userID);
	
	// 권한 읽기
	public List<GrantedAuthority> readAuthorities(String userID);

	// 권한 생성
	public void createAuthority(User user);
}
