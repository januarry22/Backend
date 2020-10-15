package com.project.example.service;

import java.util.List;

import com.project.example.domain.Stock;


public interface StockService {
	
	public List<Stock> selectStockList();
	
	public void insertStock(Stock stock);

	public void deleteStock(int stock_id);

}
