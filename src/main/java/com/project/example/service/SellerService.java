package com.project.example.service;

import java.util.List;

import com.project.example.domain.Seller;

public interface SellerService {

	public List<Seller> selectSellerList();
	
	public void insertSeller(Seller seller);

	public void deleteSeller(int seller_id);


}
