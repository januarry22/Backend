package com.project.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.example.domain.Sale;
import com.project.example.mapper.SaleMapper;


@Service("SaleServiceImpl")
public class SaleServiceImpl implements SaleService{

	@Autowired
	SaleMapper saleMapper;
	
	@Override
	public List<Sale> selectSaleList(String sale_user_id) {
		// TODO Auto-generated method stub
		return saleMapper.selectSaleList(sale_user_id);
	}

}
