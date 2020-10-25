package com.project.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.example.domain.Stock;

@Mapper
public interface StockMapper {
	
	public List<Stock> selectStockList(String stock_user_id);
	
	public void insertStock(Stock stock);

	public void deleteStock(int stock_id);

	
}
