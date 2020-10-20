package com.project.example.domain;

import java.util.Date;

public class Employee {

	private int employee_id;
	private String employee_name;
	private Date employee_birth;
	private String employee_phone;
	private String employee_rank;
	private int employee_rank_pay;
	private String employee_account_num;
	private String employee_account_bank;
	private Date employee_regi_date;
	private Date employee_quit_date;
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public Date getEmployee_birth() {
		return employee_birth;
	}
	public void setEmployee_birth(Date employee_birth) {
		this.employee_birth = employee_birth;
	}
	public String getEmployee_phone() {
		return employee_phone;
	}
	public void setEmployee_phone(String employee_phone) {
		this.employee_phone = employee_phone;
	}
	public String getEmployee_rank() {
		return employee_rank;
	}
	public void setEmployee_rank(String employee_rank) {
		this.employee_rank = employee_rank;
	}
	public int getEmployee_rank_pay() {
		return employee_rank_pay;
	}
	public void setEmployee_rank_pay(int employee_rank_pay) {
		this.employee_rank_pay = employee_rank_pay;
	}
	public String getEmployee_account_num() {
		return employee_account_num;
	}
	public void setEmployee_account_num(String employee_account_num) {
		this.employee_account_num = employee_account_num;
	}
	public String getEmployee_account_bank() {
		return employee_account_bank;
	}
	public void setEmployee_account_bank(String employee_account_bank) {
		this.employee_account_bank = employee_account_bank;
	}
	public Date getEmployee_regi_date() {
		return employee_regi_date;
	}
	public void setEmployee_regi_date(Date employee_regi_date) {
		this.employee_regi_date = employee_regi_date;
	}
	public Date getEmployee_quit_date() {
		return employee_quit_date;
	}
	public void setEmployee_quit_date(Date employee_quit_date) {
		this.employee_quit_date = employee_quit_date;
	}
	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", employee_name=" + employee_name + ", employee_birth="
				+ employee_birth + ", employee_phone=" + employee_phone + ", employee_rank=" + employee_rank
				+ ", employee_rank_pay=" + employee_rank_pay + ", employee_account_num=" + employee_account_num
				+ ", employee_account_bank=" + employee_account_bank + ", employee_regi_date=" + employee_regi_date
				+ ", employee_quit_date=" + employee_quit_date + "]";
	}
	
	
}
