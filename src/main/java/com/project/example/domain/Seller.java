package com.project.example.domain;


public class Seller {
	
	private int seller_id;
	private String seller_name;
	private int seller_tel;
	private String seller_item;
	private String seller_regdate;
	
	
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public String getSeller_name() {
		return seller_name;
	}
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	public int getSeller_tel() {
		return seller_tel;
	}
	public void setSeller_tel(int seller_tel) {
		this.seller_tel = seller_tel;
	}
	public String getSeller_item() {
		return seller_item;
	}
	public void setSeller_item(String seller_item) {
		this.seller_item = seller_item;
	}

	public String getSeller_regdate() {
		return seller_regdate;
	}
	public void setSeller_regdate(String seller_regdate) {
		this.seller_regdate = seller_regdate;
	}
	@Override
	public String toString() {
		return "Seller [seller_id=" + seller_id + ", seller_name=" + seller_name + ", seller_tel=" + seller_tel
				+ ", seller_item=" + seller_item + ", seller_regdate=" + seller_regdate + "]";
	}
	
	
}
