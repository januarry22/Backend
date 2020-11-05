package com.project.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.example.domain.Expand;
import com.project.example.service.ExpandService;

@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("api/expand")
public class ExpandController {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ExpandService expandService;
	
//	@GetMapping("/all")
//	public String allAccess() {
//		return "Public Content.";
//	}
	
	@GetMapping("/list")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> expandList(){
		List<Expand> expandList= expandService.selectExpandList();
		return ResponseEntity.ok(expandList);
	}
	
	@PostMapping("/insert")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> insertExpand(@RequestBody Expand expand){
				
		expandService.insertExpand(expand);
		
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{expand_id}")
	public ResponseEntity<?> expandDelete(@PathVariable(value="expand_id") int expand_id){
		logger.info("delete"+expand_id);
		
		expandService.deleteExpand(expand_id);
		return ResponseEntity.ok(expand_id);
	}
	
	
}
