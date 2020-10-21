package com.project.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.project.example.domain.Seller;
import com.project.example.service.SellerService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/seller")
public class SellerController {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SellerService sellerService;
	
	@GetMapping("list")
	public ResponseEntity<?> sellerList(){
		List<Seller> sellerList=sellerService.selectSellerList();
		return ResponseEntity.ok(sellerList);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertSeller(@RequestBody Seller seller){
		sellerService.insertSeller(seller);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("delete/{seller_id}")
	public ResponseEntity<?> sellerDelete(@PathVariable(value="seller_id") int seller_id){
		
		sellerService.deleteSeller(seller_id);
		return ResponseEntity.ok(seller_id);
	}
	
}
