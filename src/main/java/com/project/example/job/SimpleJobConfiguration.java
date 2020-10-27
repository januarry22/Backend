package com.project.example.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j	//log 사용을 위한 lombok 어노테이션
//@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
//@Configuration

public class SimpleJobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public Job simpleJob() {
		return jobBuilderFactory.get("simpleJob")
				.start(simpleStep1(null))
				.next(simpleStep2(null))
				.build();
	}
	// Batch Job job 이름은 별도로 지정하지 않고, builder를 통해 지정.
	
	@Bean
	@JobScope
	public Step simpleStep1(@org.springframework.beans.factory.annotation.Value("#{jobParameters[requestDate]}")String requestDate) {
		return stepBuilderFactory.get("simpleStep1")
				.tasklet((contribution, chunkContext)->{
				//	log.info("message");
				//	log.info("message");
				// return RepeatStatus.FINISHED;
					throw new IllegalArgumentException("Step1실패");
				})
				.build();
	}
	// Batch Step도 Builder를 통해 생성
	
	@Bean
	@JobScope
	public Step simpleStep2(@org.springframework.beans.factory.annotation.Value("#{jobParameters[requestDate]}")String requestDate) {
		return stepBuilderFactory.get("simpleStep2")
				.tasklet((contribution, chunkContext)->{
				//	log.info("message");
				//	log.info("message");
					System.out.print(">>>>"+requestDate);
				return RepeatStatus.FINISHED;
				})
				.build();
	}
}

