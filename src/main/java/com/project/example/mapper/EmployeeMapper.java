package com.project.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.example.domain.Employee;

@Mapper
public interface EmployeeMapper {
	
	public List<Employee> selectEmployeeList();
	
	public void insertEmployee(Employee employee);
	
	public void deleteEmployee(int employee_id);

}
