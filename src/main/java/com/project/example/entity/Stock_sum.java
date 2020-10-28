package com.project.example.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Stock_sum {

	private int id;
	private int stock_day_sum;
	private String stock_day;
	


	@Builder
	public Stock_sum(int stock_day_sum, String stock_day) {
		this.stock_day_sum = stock_day_sum;
		this.stock_day = stock_day;
	}



	public Stock_sum(int id, int stock_day_sum, String stock_day) {
		this.id = id;
		this.stock_day_sum = stock_day_sum;
		this.stock_day = stock_day;
	}
	
	
	
	
	
	
}
