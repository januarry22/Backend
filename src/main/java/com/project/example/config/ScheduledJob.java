package com.project.example.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.example.domain.Stock_sum;
import com.project.example.service.StockService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledJob {
	private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);
	
	private static final SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
	
	@Autowired StockService stockservice;
	
	
	@Scheduled(cron="0/1 * * * * *")
	public void selectSum() {
		
		List<Stock_sum> stocksumList= stockservice.selectDateSum();
		
		List<Stock_sum> stock=new ArrayList<Stock_sum>(stocksumList);
		
//		Stock_sum sum= new Stock_sum();
//		sum.setStock_day(stock_day);
//		sum.setStock_day_sum(stock_day_sum);
//		
//		stockservice.insertStockAmount(stocksumList);
		System.out.print("commit"+stock);
	}

}
