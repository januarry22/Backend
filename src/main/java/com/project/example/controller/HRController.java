package com.project.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("api/stock")
public class HRController {

	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
}
