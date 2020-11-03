package com.project.example.service;

import java.util.List;


import com.project.example.domain.Sale;


public interface SaleService {

	public List<Sale> selectSaleList(String sale_user_id);

	
}
