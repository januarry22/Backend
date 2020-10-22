package com.project.example.domain;

public class Stock {

	public int stock_id;
	public String stock_name;
	public String stock_regi_date;
	public String stock_expire_date;
	public int stock_quantity;
	
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}

	public String getStock_regi_date() {
		return stock_regi_date;
	}
	public void setStock_regi_date(String stock_regi_date) {
		this.stock_regi_date = stock_regi_date;
	}
	public String getStock_expire_date() {
		return stock_expire_date;
	}
	public void setStock_expire_date(String stock_expire_date) {
		this.stock_expire_date = stock_expire_date;
	}
	public int getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	@Override
	public String toString() {
		return "Stock [stock_id=" + stock_id + ", stock_name=" + stock_name + ", stock_regi_date=" + stock_regi_date
				+ ", stock_expire_date=" + stock_expire_date + ", stock_quantity=" + stock_quantity + "]";
	}
	
	
}
