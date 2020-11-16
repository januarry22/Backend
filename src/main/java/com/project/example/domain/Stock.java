package com.project.example.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Stock {


	private int stock_id;
	private String stock_name;
	private String stock_regi_date;
	private String stock_expire_date;
	private int stock_quantity;
	private String stock_user_id;
	
	private int stock_day_sum;
	private String stock_day;
	
	
	
	public Stock(int stock_id, String stock_regi_date, int stock_quantity) {
		this.stock_id = stock_id;
		this.stock_regi_date = stock_regi_date;
		this.stock_quantity = stock_quantity;
	}


	public Stock(String stock_regi_date, int stock_quantity) {
		this.stock_regi_date = stock_regi_date;
		this.stock_quantity = stock_quantity;
	}
	
	
	


	
	
	
}
