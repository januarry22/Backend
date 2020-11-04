package com.project.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.example.domain.Employee;
import com.project.example.mapper.EmployeeMapper;

@Service("EmployeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired EmployeeMapper employeeMapper;

	@Override
	public List<Employee> selectEmployeeList() {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployeeList();
	}

	@Override
	public void insertEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertEmployee(employee);
	}

	@Override
	public void deleteEmployee(int employee_id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteEmployee(employee_id);
	}

	@Override
	public List<Employee> selectEmployeeNameList(String employee_user_id) {
		// TODO Auto-generated method stub
		return employeeMapper.selectEmployeeNameList(employee_user_id);
	}
	
	

}
