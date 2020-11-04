package com.project.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.example.domain.Employee;
import com.project.example.service.EmployeeService;

@CrossOrigin(origins="*", maxAge=360)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private final Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public ResponseEntity<?> employeeList(){
		List<Employee> employeeList=employeeService.selectEmployeeList();
		return ResponseEntity.ok(employeeList);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertEmployee(@RequestBody Employee employee){
		employeeService.insertEmployee(employee);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{employee_id}")
	public ResponseEntity<?> employeeDelete(@PathVariable(value="employee_id") int employee_id){
		employeeService.deleteEmployee(employee_id);
		return ResponseEntity.ok(employee_id);
	}
	
	@GetMapping("/employee_nameList/{employee_user_id}")
	public ResponseEntity<?> employee_nameList(@PathVariable(value="employee_user_id") String employee_user_id){
		List<Employee> nameList=employeeService.selectEmployeeNameList(employee_user_id);
		return ResponseEntity.ok(nameList);
	}
}
