package com.project.example.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Sale {

	private int sale_deal_id;
	private String sale_user_id; 
	private String sale_deal_date;
	private String sale_deal_pay;
	private int sale_deal_price;
	private String sale_deal_item;
	
	public Sale(String sale_deal_date, int sale_deal_price) {
		this.sale_deal_date = sale_deal_date;
		this.sale_deal_price = sale_deal_price;
	}
	
	
}
