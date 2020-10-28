package com.project.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.example.domain.Stock;
import com.project.example.entity.Stock_sum;

@Mapper
public interface StockMapper {
	
	public List<Stock> selectStockList(String stock_user_id);
	
	public void insertStock(Stock stock);

	public void deleteStock(int stock_id);

	public List<Stock_sum> selectDateSum();
	
	public void insertStockAmount(Stock_sum sum);
}
