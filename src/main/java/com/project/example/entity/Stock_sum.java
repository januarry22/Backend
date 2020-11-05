package com.project.example.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Stock_sum {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String stock_user_id;
	private int stock_day_sum;
	private String stock_day;
	
	


	public Stock_sum(String stock_user_id) {
		this.stock_user_id = stock_user_id;
	}

	public Stock_sum(int stock_day_sum, String stock_day) {
		this.stock_day_sum = stock_day_sum;
		this.stock_day = stock_day;
	}

//	public Stock_sum(Long id, String stock_user_id, int stock_day_sum, String stock_day) {
//		this.id = id;
//		this.stock_user_id = stock_user_id;
//		this.stock_day_sum = stock_day_sum;
//		this.stock_day = stock_day;
//	}



	
	
	
	
	
	
	
}
