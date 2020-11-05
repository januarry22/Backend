package com.project.example.service;

import java.util.List;

import com.project.example.domain.Expand;

public interface ExpandService {

	public List<Expand> selectExpandList();
	
	public void insertExpand(Expand expand);

	public void deleteExpand(int expand_id);
	
}
