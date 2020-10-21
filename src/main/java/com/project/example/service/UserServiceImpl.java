package com.project.example.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.example.domain.User;
import com.project.example.domain.UserInfo;
import com.project.example.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userMapper.readUser(username);
		user.setAuthorities(getAuthorities(username));
		return user;
	}

	@Override
	public User readUser(String username) {	
		// TODO Auto-generated method stub
		return userMapper.readUser(username);
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		userMapper.createUser(user);
		
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities(String username) {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities=userMapper.readAuthorities(username);
		
		return authorities;
	}

	@Override
	public void createAuthority(User user) {
		// TODO Auto-generated method stub
		userMapper.createAuthority(user);
		
	}

	@Override
	public UserInfo readUser_refresh(String username) {
		// TODO Auto-generated method stub
		return userMapper.readUser_refresh(username);
	}

	@Override
	public List<GrantedAuthority> readAuthorities_refresh(String username) {
		// TODO Auto-generated method stub
		return userMapper.readAuthorities_refresh(username);
	}

	
	
}
