package com.project.example.domain;


public class Expand {

	public int expand_id;
	public String expand_content;
	public String expand_date;
	public int expand_price;
	public String expand_val;
	
	public int getExpand_id() {
		return expand_id;
	}
	public void setExpand_id(int expand_id) {
		this.expand_id = expand_id;
	}
	public String getExpand_content() {
		return expand_content;
	}
	public void setExpand_content(String expand_content) {
		this.expand_content = expand_content;
	}

	public String getExpand_date() {
		return expand_date;
	}
	public void setExpand_date(String expand_date) {
		this.expand_date = expand_date;
	}
	public int getExpand_price() {
		return expand_price;
	}
	public void setExpand_price(int expand_price) {
		this.expand_price = expand_price;
	}
	public String getExpand_val() {
		return expand_val;
	}
	public void setExpand_val(String expand_val) {
		this.expand_val = expand_val;
	}
	@Override
	public String toString() {
		return "Expand [expand_id=" + expand_id + ", expand_content=" + expand_content + ", expand_date=" + expand_date
				+ ", expand_price=" + expand_price + ", expand_val=" + expand_val + "]";
	}
	
	
	
}
