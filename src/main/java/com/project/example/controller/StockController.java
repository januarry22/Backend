package com.project.example.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.example.domain.Stock;
import com.project.example.service.StockService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("api/stock")
public class StockController {
	

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private final JobLauncher stockLauncher;
	
	@Autowired
	private final Job stockBatchJob;
	
//	private JobParameters stockParam() {
//		Map<String, JobParameter> param= new HashMap<>();
//		param.put()
//	}
	
	@GetMapping("/list/{stock_user_id}")
	public ResponseEntity<?> stockList(@PathVariable(value="stock_user_id") String stock_user_id){
		List<Stock> stList=stockService.selectStockList(stock_user_id);
		
		try {
			JobParameters jobParameters= new JobParametersBuilder()
					.addString("stock_user_id", stock_user_id)
					.toJobParameters();
			stockLauncher.run(stockBatchJob, jobParameters);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		return ResponseEntity.ok(stList);
	}
	
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertStock(@RequestBody Stock stock){
		stockService.insertStock(stock);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{stock_id}")
	public ResponseEntity<?> stockDelete(@PathVariable(value="stock_id") int stock_id){
		stockService.deleteStock(stock_id);
		return ResponseEntity.ok(stock_id);
	}


}
