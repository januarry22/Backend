package com.project.example.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.project.example.entity.Stock_sum;


//import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@Configuration
public class JdbcCursorItemReaderJobConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
    public DataSource dataSource; // DataSource DI
	
	private static final int chunkSize=10;
	
	@Bean
	public Job jdbcCursorItemReaderJob() {
		return jobBuilderFactory.get("jdbcCursorItemReaderJob")
				.start(jdbcCursorItemReaderStep())
				.build();
	}
	
	
	@Bean 
	public Step jdbcCursorItemReaderStep() {
		return stepBuilderFactory.get("jdbcCursorItemReaderStep")
				.<Stock_sum, Stock_sum>chunk(chunkSize)
				.reader(jdbcCursorItemReader())
				.writer(jdbcCursorItemWriter())
				.build();
	}
	
	@Bean
	public JdbcCursorItemReader<Stock_sum> jdbcCursorItemReader(){
		return new JdbcCursorItemReaderBuilder<Stock_sum>()
				.fetchSize(chunkSize)
				.dataSource(dataSource)
				.rowMapper(new BeanPropertyRowMapper<>(Stock_sum.class))
				.sql("SELECT id, stock_day_sum, stock_day FROM Stock_sum")
				.name("jdbcCursorItemReader")
				.build();
	}
	
	private ItemWriter<Stock_sum> jdbcCursorItemWriter(){
//		return list -> {
//			for(Stock_sum stock: list) {
//				System.out.print("Current Stock="+stock);
//			}
//		};
		return new JdbcBatchItemWriterBuilder<Stock_sum>()
				.dataSource(dataSource)
				.sql("insert into StockDaySum(id, stock_day_sum, stock_day) values(:id, :stock_day_sum, :stock_day)")
				.beanMapped()		//assertUpdates, columnMapped
				.build();
	}
}
