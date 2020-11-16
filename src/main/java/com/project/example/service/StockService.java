package com.project.example.service;

import java.util.List;

import com.project.example.domain.Stock;
import com.project.example.entity.Stock_sum;


public interface StockService {
	
	public List<Stock> selectStockList(String stock_user_id);
	
	public void insertStock(Stock stock);

	public void deleteStock(int stock_id);
	
	public List<Stock> selectDaySum(String stock_user_id);
	
	//public void insertStockAmount(Stock_sum sum);

}
