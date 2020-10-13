package com.project.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.example.domain.Expand;
import com.project.example.mapper.ExpandMapper;

@Service("ExpandServiceImpl")
public class ExpandServiceImpl implements ExpandService{

	@Autowired ExpandMapper expandMapper;

	@Override
	public List<Expand> selectExpandList() {
		// TODO Auto-generated method stub
		return expandMapper.selectExpandList();
	}

	@Override
	public void insertExpand(Expand expand) {
		// TODO Auto-generated method stub
		expandMapper.insertExpand(expand);
	}
	
	
	
}
