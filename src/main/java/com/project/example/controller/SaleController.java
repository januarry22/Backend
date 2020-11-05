package com.project.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.example.domain.Sale;
import com.project.example.service.SaleService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("api/sale")
public class SaleController {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private SaleService saleService;
	
	@GetMapping("/list/{sale_user_id}")
	public ResponseEntity<?> saleList(@PathVariable(value="sale_user_id") String sale_user_id){
		
		List<Sale> sale_List=saleService.selectSaleList(sale_user_id);
		
		return ResponseEntity.ok(sale_List);
	}
}
