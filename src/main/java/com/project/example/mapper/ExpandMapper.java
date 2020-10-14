package com.project.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.example.domain.Expand;

@Mapper
public interface ExpandMapper {

	// 지출 내역 조회
	public List<Expand> selectExpandList();
	
	// 지출 입력
	public void insertExpand(Expand expand);

	public void deleteExpand(int expand_id);
}
