package com.project.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.example.domain.Seller;

@Mapper
public interface SellerMapper {


		public List<Seller> selectSellerList();
		
		public void insertSeller(Seller seller);

		public void deleteSeller(int seller_id);


}
