package com.project.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.example.domain.Stock;
import com.project.example.mapper.StockMapper;


@Service("StockServiceImpl")
public class StockServiceImpl implements StockService{

	@Autowired StockMapper stockMapper;
	
	@Override
	public List<Stock> selectStockList() {
		// TODO Auto-generated method stub
		return stockMapper.selectStockList();
	}

	@Override
	public void insertStock(Stock stock) {
		// TODO Auto-generated method stub
		stockMapper.insertStock(stock);
	}

	@Override
	public void deleteStock(int stock_id) {
		// TODO Auto-generated method stub
		stockMapper.deleteStock(stock_id);
	}

}
