package com.project.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.example.domain.Sale;

@Mapper
public interface SaleMapper {

	public List<Sale> selectSaleList(String sale_user_id);
	
}
