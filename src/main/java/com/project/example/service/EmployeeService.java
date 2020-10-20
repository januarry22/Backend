package com.project.example.service;

import java.util.List;

import com.project.example.domain.Employee;

public interface EmployeeService {

	public List<Employee> selectEmployeeList();
	
	public void insertEmployee(Employee employee);
	
	public void deleteEmployee(int employee_id);
}
