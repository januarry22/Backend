package com.project.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class SaleSum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int sale_amount;
	private String sale_date;
	
	
//	public Sale_sum(long id, int sale_sum, String sale_date) {
//		this.id = id;
//		this.sale_sum = sale_sum;
//		this.sale_date = sale_date;
//	}


	public SaleSum(int sale_amount, String sale_date) {
		this.sale_amount = sale_amount;
		this.sale_date = sale_date;
	}
	
}
