package com.project.example.job;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.project.example.domain.Stock_sum;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StockBatchConfiguration {
	public static final String JOB_NAME="stockBatchJob";
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
    public DataSource dataSource; // DataSource DI
	
	private int chunkSize;
	
	@org.springframework.beans.factory.annotation.Value("${chunkSize:1000}")
	public void setChunkSize(int chunkSize) {
		this.chunkSize=chunkSize;
	}
	
	@Bean
	public Job StockJob() throws Exception{
		return jobBuilderFactory.get(JOB_NAME)
				.start(StockStep())
				.build();
	}
	
	@Bean
	public Step StockStep() throws Exception{
		return stepBuilderFactory.get("stockBatchJob")
				.<Stock_sum, Stock_sum>chunk(chunkSize)
				.reader(stockItemReader(null))
				.writer(stockItmeWriter())
				.build();
	}
	
	@Bean
	@StepScope
	public JdbcPagingItemReader<Stock_sum> stockItemReader(
			@Value("#{jobParameters[stockDay]}") String stockDay) throws Exception{
		
		Map<String, Object> params= new HashMap<>();
		params.put("stockDay", LocalDate.parse(stockDay));
		
		 SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
	        queryProvider.setDataSource(dataSource);
	        queryProvider.setSelectClause("stock_day, sum(amount) as amount_sum");
	        queryProvider.setFromClause("from sales");
	        queryProvider.setWhereClause("where order_date =:orderDate");
	        queryProvider.setGroupClause("group by order_date");
	        queryProvider.setSortKey("order_date");
	        
	        return new JdbcPagingItemReaderBuilder<StockSum>()
	        		.name("stockItemReader")
	                .pageSize(chunkSize)
	                .fetchSize(chunkSize)
	                .dataSource(dataSource)
	                .rowMapper(new BeanPropertyRowMapper<>(StockSum.class))
	                .queryProvider(queryProvider.getObject())
	                .parameterValues(params)
	                .build();
	}
	
	@Bean
	public JdbcBatchItemWriter<StockSum> stockItmeWriter(){
		return new JdbcBatchItemWriterBuilder<StockSum>()
				.dataSource(dataSource)
				.sql("insert into Stock_sum() values()")
				.beanMapped()
				.build();
	}
			
}
