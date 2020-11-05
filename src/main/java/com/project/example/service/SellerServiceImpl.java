package com.project.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.example.domain.Seller;
import com.project.example.mapper.SellerMapper;

@Service("SellerServiceImpl")
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	SellerMapper sellermapper;

	@Override
	public List<Seller> selectSellerList() {
		// TODO Auto-generated method stub
		return sellermapper.selectSellerList();
	}

	@Override
	public void insertSeller(Seller seller) {
		// TODO Auto-generated method stub
		sellermapper.insertSeller(seller);
	}

	@Override
	public void deleteSeller(int seller_id) {
		// TODO Auto-generated method stub
		sellermapper.deleteSeller(seller_id);
	}
	
	

}
