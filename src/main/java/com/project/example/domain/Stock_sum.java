package com.project.example.domain;


public class Stock_sum {

	private int stock_day_sum;
	private String stock_day;
	
	
	
	public int getStock_day_sum() {
		return stock_day_sum;
	}



	public void setStock_day_sum(int stock_day_sum) {
		this.stock_day_sum = stock_day_sum;
	}



	public String getStock_day() {
		return stock_day;
	}



	public void setStock_day(String stock_day) {
		this.stock_day = stock_day;
	}



	@Override
	public String toString() {
		return "Stock_sum [stock_day_sum=" + stock_day_sum + ", stock_day=" + stock_day + "]";
	}
	
	
}
