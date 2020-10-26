package com.project.example.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledJob {
	private static final Logger log = LoggerFactory.getLogger(ScheduledJob.class);
	
	private static final SimpleDateFormat dateFormat= new SimpleDateFormat("H:mm:ss");
	
	@Scheduled(cron="0 30 18 * * *")
	public void selectSum() {
		
	}

}
